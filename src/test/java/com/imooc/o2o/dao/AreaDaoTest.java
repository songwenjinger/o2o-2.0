package com.imooc.o2o.dao;



import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

/**
 * 用来配置spring和junit 整合，junit启动时加载springIOC容器
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryDao() {
        List<Area> areaList = areaDao.queryArea();
        Iterator<Area> iterator = areaList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}
