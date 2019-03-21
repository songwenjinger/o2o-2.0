package com.imooc.o2o.dao;


import com.imooc.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * 用来配置spring和junit 整合，junit启动时加载springIOC容器
 */
public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    public void testQueryDao()
    {
        List<Area> areaList=areaDao.queryArea();
        Iterator<Area> iterator=areaList.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
    }


}
