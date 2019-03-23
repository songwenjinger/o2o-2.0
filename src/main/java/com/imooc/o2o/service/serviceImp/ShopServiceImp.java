package com.imooc.o2o.service.serviceImp;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exception.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.MultiPartFileToFile;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.QiNiuYunFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImp implements ShopService {
    @Autowired
    private ShopDao shopDao;
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, MultipartFile shopImg) {
        //空值判断
        if(shop==null)
        {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try{
            //未上架，在审核总
            //给店铺信息赋初始值
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectdNum=shopDao.insertShop(shop);
            if(effectdNum<=0)
            {
                throw new RuntimeException("店铺创建失败");
                //只有抛出RuntimeException事务才会回滚，得以输出
                //如果是exception事务就会已经提交了，不能回滚
            }else
            {
                if(shopImg!=null)
                {
                    //存储图片
                    try {
                        //上传图片到七牛云，然后得到图片下载的url
                        addShopImg(shop, shopImg);
                    }catch (Exception e)
                    {
                        throw new ShopOperationException("addShopImg error："+e.getMessage());
                    }
                    effectdNum=shopDao.updateShop(shop);
                    if(effectdNum<=0)
                    {
                        throw new RuntimeException("更新图片地址失败");
                    }
                }
            }
        }catch(Exception e)
        {
            throw new RuntimeException("addShop error:"+e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK,shop);
    }
    private void addShopImg(Shop shop, MultipartFile shopImg) throws Exception {
        //获取shop图片目录的相对路径
       File temp= MultiPartFileToFile.multipartFileToFile(shopImg);
        QiNiuYunFileUtils.fileUpload(temp);
        //将图片存储，返回图片的绝对值路径
        String shopImgAddr=QiNiuYunFileUtils.getFileDownloadUrl(temp.getName()) ;
        shop.setShopImg(shopImgAddr);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, MultipartFile shopImg) throws IOException {
        if(shop==null||shop.getShopId()==null)
        {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else
        {
        //1. 判断是否需要处理图片
            if(shopImg!=null){
                Shop tempShop=shopDao.queryByShopId(shop.getShopId());
              try {
                    addShopImg(shop,shopImg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        //2. 更新店铺信息
            shop.setLastEditTime(new Date());
            int effectedNum=shopDao.updateShop(shop);
            if(effectedNum<=0)
            {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            }else
            {
                shop=shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS,shop);
            }
        }
       // return null;
    }

    /**
     * 根据shopCondition分页返回相应列表数据
     * 之所以返回ShopExecution是因为该类中将shopList和count整合在了一起
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
       int rowIndex= PageCalculator.calculateRowIndex(pageIndex,pageSize);
       List<Shop> shopList=shopDao.queryShopList(shopCondition,rowIndex,pageSize);
       int count=shopDao.queryShopCount(shopCondition);
       ShopExecution shopExecution=new ShopExecution();
       if(shopList!=null)
       {
           shopExecution.setShopList(shopList);
           shopExecution.setCount(count);
       }else
       {
           shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
       }
        return shopExecution;
    }

}
