package com.imooc.o2o.dao;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.imooc.o2o.entity.HeadLine;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HeadLineDaoTest extends BaseTest {
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
