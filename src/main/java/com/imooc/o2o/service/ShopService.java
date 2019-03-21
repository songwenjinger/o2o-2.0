package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface ShopService {
    ShopExecution addShop(Shop shop, MultipartFile shopImg );
    Shop getByShopId(long shopId);
    ShopExecution modifyShop(Shop shop,MultipartFile shopImg) throws IOException;
    public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
}
