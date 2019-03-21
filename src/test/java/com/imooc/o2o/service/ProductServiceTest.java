package com.imooc.o2o.service;

import com.imooc.o2o.dao.BaseTest;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.util.ImageUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;
    @Test
  //  @Ignore
    public void testAddShop() throws Exception {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(20L);
        product.setShop(shop);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(15L);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品1");
        product.setProductDesc("测试商品1描述");
        product.setPriority(11);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        product.setLastEditTime(new Date());
        product.setCreateTime(new Date());
        //缩略图
        String filePath0 = "F://abc.JPG";
        List<MultipartFile> productImgList = new ArrayList<>();
        String filePath1 = "F://def.JPG";
        MultipartFile productImg1 = ImageUtil.path2MultipartFile(filePath1);
        productImgList.add(productImg1);
        String filePath2 = "F://haha.JPG";
        MultipartFile productImg2 = ImageUtil.path2MultipartFile(filePath2);
        productImgList.add(productImg2);
        ProductExecution se = productService.addProduct(product, ImageUtil.path2MultipartFile(filePath0),
                productImgList);
        System.out.println("ProductExecution.state" + se.getState());
        System.out.println("ProductExecution.stateInfo" + se.getStateInfo());
    }

    @Test
    public void testModifyShop() throws IOException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(20L);
        product.setShop(shop);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(13L);
        product.setProductId(17L);
        product.setProductCategory(productCategory);
        product.setProductName("测试商品2");
        product.setProductDesc("测试商品2描述");
        product.setPriority(22);
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        product.setLastEditTime(new Date());
        product.setCreateTime(new Date());
        String filePath0 = "F:\\abc.jpg";
        List<MultipartFile> productImgList = new ArrayList<>();
        String filePath1 = "F:\\def.jpg";
        MultipartFile productImg1 = ImageUtil.path2MultipartFile(filePath1);
        productImgList.add(productImg1);
        String filePath2 = "F:\\abc.jpg";
        MultipartFile productImg2 = ImageUtil.path2MultipartFile(filePath2);
        productImgList.add(productImg2);
        ProductExecution se = productService.modifyProduct(product, ImageUtil.path2MultipartFile(filePath0),
                productImgList);
        System.out.println("ProductExecution.state" + se.getState());
        System.out.println("ProductExecution.stateInfo" + se.getStateInfo());
    }
    @Test
    public void testGetProductList()
    {
        Product product = new Product();
        ProductExecution productExecution = productService.getProductList(product,0,3);
        System.out.println(productExecution.getProductList());
    }

}
