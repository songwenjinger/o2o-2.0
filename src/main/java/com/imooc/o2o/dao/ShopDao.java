package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

public interface ShopDao {
    /**
     * 查询 通过shop id查询店铺
     */
    /**
     * 新增店铺
     *
     * @param
     * @return
     */
    public Shop queryByShopId(Long shopId);

    public int insertShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop
     * @return
     */
    public int updateShop(Shop shop);

    /**
     * 分页查询店铺，可输入的条件有:店铺名（模糊），店铺状态，店铺类别，区域ID，owner
     * @param shopCondition
     * @param rowIndex 从第几行开始取
     * @param pageSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    /**
     * 查询符合分页结果的数据的总条数queryShopList
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition")Shop shopCondition);


}
