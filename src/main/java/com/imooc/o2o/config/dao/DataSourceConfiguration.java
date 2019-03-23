package com.imooc.o2o.config.dao;

import com.imooc.o2o.util.DESUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
//添加这个注解表示这个类是属于spring容器的
//配置mybatis mapper的扫描路径
@MapperScan("com.imooc.o2o.dao")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUserName;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    //读取四个变量，注入datasource
    /**
     * 生成与spring-dao.xml对应的bean datasource
     */
    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        //生成datasource实例
       ComboPooledDataSource dataSource=new ComboPooledDataSource();
       //跟配置文件一样设置以下信息
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        //用户名是已经加密过的，不能直接设置，会报错
        dataSource.setUser(DESUtils.getDecryptString(jdbcUserName));
        dataSource.setPassword(DESUtils.getDecryptString(jdbcPassword));
        dataSource.setMaxPoolSize(30);
        dataSource.setMinPoolSize(10);
        //关闭连接后不自动commit
        dataSource.setAutoCommitOnClose(false);
        //设置连接超时的时间
        dataSource.setCheckoutTimeout(10000);
        //连接失败重新尝试的次数
        dataSource.setAcquireRetryAttempts(2);
        return dataSource;
    }
}
