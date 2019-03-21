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
        List<HeadLine> headLineList=headLineDao.queryHeadLine(new HeadLine());
        System.out.println(headLineList.size());
    }


}
