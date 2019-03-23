package com.imooc.o2o.service.serviceImp;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.dto.HeadLineExecution;
import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.service.HeadLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class HeadLineServiceImpl implements HeadLineService {
	@Autowired
	private HeadLineDao headLineDao;
	@Autowired
	private JedisUtil.Strings jedisStrings;
	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Override
	@Transactional
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition)throws IOException
	{
		List<HeadLine> headLineList=new ArrayList<HeadLine>();
		//定义key
		String key=HEADLINEKEY;
		ObjectMapper mapper=new ObjectMapper();
		//根据mapper中的查询条件拼装key
		if(headLineCondition!=null&&headLineCondition.getEnableStatus()!=null)
		{
			key=key+"_"+headLineCondition.getEnableStatus();
		}
		if(!jedisKeys.exists(key))
		{
			try
			{
				headLineList=headLineDao.queryHeadLine(headLineCondition);
				String jsonString=mapper.writeValueAsString(headLineList);
				jedisStrings.set(key,jsonString);
			}catch (Exception e)
			{
				e.printStackTrace();
			}
		}else
		{
			try{
				String jsonString=jedisStrings.get(key);
				JavaType javaType=mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
				headLineList=mapper.readValue(jsonString,javaType);
			}catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	return headLineList;

	}

	@Override
	public HeadLineExecution addHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
		return null;
	}

	@Override
	public HeadLineExecution modifyHeadLine(HeadLine headLine, CommonsMultipartFile thumbnail) {
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLine(long headLineId) {
		return null;
	}

	@Override
	public HeadLineExecution removeHeadLineList(List<Long> headLineIdList) {
		return null;
	}
}
