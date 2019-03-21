package com.imooc.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 对spring-dao配置的关键信息进行解密
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
//    需要加密的字段数组
    private String[] encryptPropNames={"jdbc.username","jdbc.password"};
    /**
     * 对关键属性进行转换
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
       if (isEncryptProp(propertyName))
       {
           String decryptValue=DESUtils.getEncryptString(propertyValue);
           return decryptValue;

       }else
       {
           return propertyValue;
       }
    }
    /**
     * 判断该属性是否已经加密
     */
    private boolean isEncryptProp(String propertyName)
    {
        for (String encryptpropertyName:encryptPropNames)
        {
            if (encryptpropertyName.equals(propertyName))
            {
                return true;
            }
        }
        return false;
    }


}
