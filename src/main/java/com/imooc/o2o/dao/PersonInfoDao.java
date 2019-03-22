package com.imooc.o2o.dao;

import java.util.List;

import com.imooc.o2o.entity.PersonInfo;
import org.apache.ibatis.annotations.Param;


public interface PersonInfoDao {

	/**
	 * 
	 * @param personInfoCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<PersonInfo> queryPersonInfoList(
            @Param("personInfoCondition") PersonInfo personInfoCondition,
            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 
	 * @param personInfoCondition
	 * @return
	 */
	int queryPersonInfoCount(
            @Param("personInfoCondition") PersonInfo personInfoCondition);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	PersonInfo queryPersonInfoById(long userId);


	int insertPersonInfo(PersonInfo personInfo);


	int updatePersonInfo(PersonInfo personInfo);

	int deletePersonInfo(long userId);
}
