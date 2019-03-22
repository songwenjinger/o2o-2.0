package com.imooc.o2o.service.serviceImp;

import java.util.Date;

import com.imooc.o2o.dao.LocalAuthDao;
import com.imooc.o2o.dao.PersonInfoDao;
import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.enums.LocalAuthStateEnum;
import com.imooc.o2o.service.LocalAuthService;
import com.imooc.o2o.util.FileUtil;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.util.locale.LocaleExtensions;


@Service
public class LocalAuthServiceImpl implements LocalAuthService {

    @Autowired
    private LocalAuthDao localAuthDao;

    @Override
    public LocalAuth getLocalAuthByUserNameAndPwd(String userName,
                                                  String password) {
        return localAuthDao.queryLocalByUserNameAndPwd(userName,MD5.getMd5(password));
    }

    @Override
    public LocalAuth getLocalAuthByUserId(long userId) {
        return localAuthDao.queryLocalByUserId(userId);
    }



    @Override
    @Transactional
    public LocalAuthExecution modifyLocalAuth(String userName,
                                              String password, String newPassword) {
        if ( userName != null && password != null
                && newPassword != null && !password.equals(newPassword)) {
            System.out.println("进入函数");
            try {
                int effectedNum = localAuthDao.updateLocalAuth(
                        userName, MD5.getMd5(password),
                        MD5.getMd5(newPassword), new Date());
             //   System.out.println(effectedNum);
                if (effectedNum <0) {
                    throw new RuntimeException("更新密码失败");
                }
                return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new RuntimeException("更新密码失败:" + e.toString());
            }
        } else {
            return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
        }
    }

    @Override
    public int addLocalAuth(Long userId, String userName, String password) {
        LocalAuth localAuth=new LocalAuth();
        localAuth.setUserId(userId);
        localAuth.setUserName(userName);
        localAuth.setPassword(MD5.getMd5(password));
        return localAuthDao.insertLocalAuth(localAuth);
        }

    private void addProfileImg(LocalAuth localAuth,
                               CommonsMultipartFile profileImg) {
	/*	String dest = FileUtil.getPersonInfoImagePath();
		String profileImgAddr = ImageUtil.generateThumbnail(profileImg, dest);
		localAuth.getPersonInfo().setProfileImg(profileImgAddr);*/
    }

}
