package com.imooc.o2o.service;

import com.imooc.o2o.dao.BaseTest;
import com.imooc.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Iterator;
import java.util.List;

public class AreaServiceTest extends BaseTest {
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
