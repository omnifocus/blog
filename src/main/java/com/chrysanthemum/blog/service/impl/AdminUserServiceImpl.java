package com.chrysanthemum.blog.service.impl;

import com.chrysanthemum.blog.bean.AdminUser;
import com.chrysanthemum.blog.bean.AdminUserExample;
import com.chrysanthemum.blog.dao.AdminUserDao;
import com.chrysanthemum.blog.service.AdminUserService;
import com.chrysanthemum.blog.utility.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public AdminUser login(String username, String password) {

        //对密码进行md5加密
        String passwordMd5 = MD5Utils.MD5Encode(password, "UTF-8");
        AdminUserExample example = new AdminUserExample();
        example.createCriteria().andLoginUserNameEqualTo(username).andLoginPasswordEqualTo(passwordMd5).andLockedEqualTo(Byte.valueOf("0"));
        List<AdminUser> adminUserList = adminUserDao.selectByExample(example);
        return  adminUserList.size() == 0 ? null : adminUserList.get(0);
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return adminUserDao.selectByPrimaryKey(loginUserId);
    }

    /*
        根据原密码 看能否查出来 用户
            如果能，更新
            不能，返回false
     */
    @Override
    public boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = adminUserDao.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Utils.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Utils.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                if (adminUserDao.updateByPrimaryKeySelective(adminUser) > 0) {
                    //修改成功则返回true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = adminUserDao.selectByPrimaryKey(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //修改信息
            adminUser.setLoginUserName(loginUserName);
            adminUser.setNickName(nickName);
            if (adminUserDao.updateByPrimaryKeySelective(adminUser) > 0) {
                //修改成功则返回true
                return true;
            }
        }
        return false;
    }
}
