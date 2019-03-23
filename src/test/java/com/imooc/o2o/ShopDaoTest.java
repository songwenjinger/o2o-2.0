package com.imooc.o2o;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopDaoTest {
    @Autowired
    private ShopDao shopDao;
    @Test
    public void testQueryByShopId()
    {
        long shopId=15L;
        Shop shop=shopDao.queryByShopId(shopId);
        System.out.println(shop);
    }
    @Test
    @Ignore
    public void testInsertShop()
    {
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        Area area=new Area();
        ShopCategory shopCategory=new ShopCategory();
        owner.setUserId(8L);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        int effectedNum=shopDao.insertShop(shop);
        System.out.println(effectedNum);
    }
    @Test
    @Ignore
    public void testUpdateShop()
    {
        Shop shop=new Shop();
        shop.setShopId(30L);
        shop.setShopDesc("测试中");
        shop.setShopAddr("测试地址");
        int effectNum=shopDao.updateShop(shop);
        System.out.println(effectNum);
    }

    @Test
    public void testQueryShopList()
    {
        Shop shopCondition=new Shop();
        PersonInfo owner=new PersonInfo();
        owner.setUserId(8L);
        shopCondition.setOwner(owner);
        List<Shop> shopList=shopDao.queryShopList(shopCondition,0,5);
        int count=shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表的大小"+shopList.size());
        System.out.println("店铺总数"+count);
        ShopCategory sc=new ShopCategory();
        sc.setShopCategoryId(22L);

        ShopCategory a=new ShopCategory();
        a.setShopCategoryId(12L);
        sc.setParent(a);
        shopCondition.setShopCategory(sc);

        shopList=shopDao.queryShopList(shopCondition,0,5);
        count  =shopDao.queryShopCount(shopCondition);
        System.out.println(shopList.size());
        System.out.println(count);
    }

}
