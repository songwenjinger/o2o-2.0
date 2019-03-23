package com.imooc.o2o;

import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.entity.HeadLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadLineDaoTest  {
    @Autowired
    private HeadLineDao headLineDao;
    @Test
    public void testQueryArea()
    {
        HeadLine headLine=new HeadLine();
        headLine.setEnableStatus(0);
        List<HeadLine> headLineList=headLineDao.queryHeadLine(headLine);
        System.out.println(headLineList.size());
    }


}
