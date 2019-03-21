package com.imooc.o2o.web.ShopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
    /**
     * 转发
     */
    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        /**
         * 配置文件中已经设置了前缀和后缀
         */
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList() {
        /**
         * 配置文件中已经设置了前缀和后缀
         */
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        /**
         * 配置文件中已经设置了前缀和后缀
         */
        return "shop/shopmanagement";
    }
    @RequestMapping(value = "/productcategorymanagement",method = RequestMethod.GET)
    public String productCategoryManagement() {
        /**
         * 配置文件中已经设置了前缀和后缀
         */
        return "shop/productcategorymanagement";
    }
    @RequestMapping(value = "/productoperation")
    public String productOperation()
    {
        return "shop/productoperation";
    }
    @RequestMapping(value = "/productmanagement")
    public String productManagement()
    {
        //转发至商品管理页面
        return "shop/productmanagement";
    }

}
