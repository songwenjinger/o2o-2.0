package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDaoTest extends BaseTest{
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Test
    public void testQueryShopCategory()
    {
        /**
         * 传参数为空时，会返回所有的列表
         */
        List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(null);
        System.out.println(shopCategoryList.size());
    }
}
