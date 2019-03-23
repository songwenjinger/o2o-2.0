package com.imooc.o2o;

import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopCategoryDaoTest{
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
