package com.imooc.o2o.service;


import com.imooc.o2o.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest  {
    @Autowired
    private AreaService areaService;
    @Autowired
    private CacheService cacheService;
    @Test
    public void testGetAreaList()
    {
        List<Area> areaList=areaService.getAreaList();
        Iterator<Area> iterator=areaList.iterator();
        while(iterator.hasNext())
        {
            System.out.println(iterator.next());
        }
       // cacheService.removeFromCache(areaService.AREALISTKEY);
    }

}
