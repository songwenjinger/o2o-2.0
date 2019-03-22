package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;



public interface LocalAuthService {
    /**
     * @param userName
     * 通过账号和密码获取平台账号信息
     * @return
     */
    LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);

    /**
     * @param userId
     * 通过userId获取用户的信息
     * @return
     */
    LocalAuth getLocalAuthByUserId(long userId);

    /**
     * 修改用户名和密码
     * @param userId
     * @param userName
     * @param password
     * @param newPassword
     * @return
     */

    LocalAuthExecution modifyLocalAuth( String userName,
                                       String password, String newPassword);
    int addLocalAuth(Long userId,String userName,String password);
}
