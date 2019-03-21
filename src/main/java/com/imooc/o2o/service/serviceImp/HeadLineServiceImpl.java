package com.imooc.o2o.service.serviceImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.dto.HeadLineExecution;
import com.imooc.o2o.entity.HeadLine;
import com.imooc.o2o.enums.HeadLineStateEnum;
import com.imooc.o2o.service.HeadLineService;
import com.imooc.o2o.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HeadLineServiceImpl implements HeadLineService {
	@Autowired
	private HeadLineDao headLineDao;
	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition)throws IOException
	{
		return headLineDao.queryHeadLine(headLineCondition);
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
