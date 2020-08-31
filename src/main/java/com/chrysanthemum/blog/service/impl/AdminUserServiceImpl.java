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
}
