package com.imooc.o2o.service.serviceImp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.exception.AreaOperationException;
import com.imooc.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImp implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    //  private static String AREALISTKEY="areaList";
    @Override
    @Transactional
    public List<Area> getAreaList() {
        //从redis中取数据，如果取不到数据，再去数据库中取数据
        String key = AREALISTKEY;
        List<Area> areaList = null;
        //将获取到的对象转换成string形式存储
        ObjectMapper mapper = new ObjectMapper();
        if (!jedisKeys.exists(key)) {
            //如过redis中不存在，就从数据库中获取
            areaList = areaDao.queryArea();
            String jsonString = null;
            try {
                jsonString = mapper.writeValueAsString(areaList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
            jedisStrings.set(key, jsonString);
        } else {
            String jsonString = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areaList = mapper.readValue(jsonString, javaType);
            } catch (Exception e) {
                e.printStackTrace();
                throw new AreaOperationException(e.getMessage());
            }
        }
        return areaList;
    }
}
