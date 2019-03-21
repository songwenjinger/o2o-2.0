package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 通过shop id查询店铺商品类别
     * 返回某店铺下面的所有商品的类别
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

    /**
     * 批量添加
     * @param productCategoryList
     * @return
     */
    int batchInsertProductCategory(List<ProductCategory> productCategoryList);
    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId")long shopId);
}
