package com.imooc.o2o.service;

import java.io.IOException;
import java.util.List;

import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.dto.HeadLineExecution;
import com.imooc.o2o.entity.HeadLine;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface HeadLineService {

	/**
	 * 
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	public static final String HEADLINEKEY="headline";
	List<HeadLine> getHeadLineList(HeadLine headLineCondition)
			throws IOException;

	/**
	 * 
	 * @param headLine
	 * @param thumbnail
	 * @return
	 */
	HeadLineExecution addHeadLine(HeadLine headLine,
								  CommonsMultipartFile thumbnail);

	/**
	 * 
	 * @param headLine
	 * @param thumbnail
	 * @return
	 */
	HeadLineExecution modifyHeadLine(HeadLine headLine,
                                     CommonsMultipartFile thumbnail);

	/**
	 * 
	 * @param headLineId
	 * @return
	 */
	HeadLineExecution removeHeadLine(long headLineId);

	/**
	 * 
	 * @param headLineIdList
	 * @return
	 */
	HeadLineExecution removeHeadLineList(List<Long> headLineIdList);

}
