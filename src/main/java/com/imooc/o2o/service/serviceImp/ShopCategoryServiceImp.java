package com.imooc.o2o.service.serviceImp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImp implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    @Transactional
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        //定义redis中的key
        String key = SCLISTKEY;
        ObjectMapper mapper = new ObjectMapper();
        if (shopCategoryCondition == null) {
            key = key + "_allfirstlevelshopcategory";
        } else if (shopCategoryCondition != null && shopCategoryCondition.getParent() != null && shopCategoryCondition.getParent().getShopCategoryId() != null) {
            //列出某个parentId下面的所有子类
            key = key + "_parent" + shopCategoryCondition.getParent().getShopCategoryId();
        } else if (shopCategoryCondition != null) {
            key = key + "_allsecondlevelshopcategory";
        }
        if (!jedisKeys.exists(key)) {
            try {
                // 从DB中加载
                shopCategoryList = shopCategoryDao.queryShopCategory(shopCategoryCondition);
                // 将相关的实体类集合转换成string,存入redis里面对应的key中
                String jsonString = mapper.writeValueAsString(shopCategoryList);
                jedisStrings.set(key, jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
        } else {
            // 否则直接从redis中获取
            try {
                String jsonString = jedisStrings.get(key);
                // 指定要将string转换成的集合类型
                JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
                // 将相关key对应的value里的的string转换成java对象的实体类集合
                shopCategoryList = mapper.readValue(jsonString, javaType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return shopCategoryList;
    }
}
