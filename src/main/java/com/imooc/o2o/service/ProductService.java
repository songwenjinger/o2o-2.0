package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    /**
     * 查询商品列表分页
     *
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    Product getProductById(long productId);

    ProductExecution addProduct(Product product, MultipartFile thumbnail, List<MultipartFile> productImgs)
            throws Exception;

    ProductExecution modifyProduct(Product product, MultipartFile thumbnail,
                                   List<MultipartFile> productImgs) throws RuntimeException;
}

