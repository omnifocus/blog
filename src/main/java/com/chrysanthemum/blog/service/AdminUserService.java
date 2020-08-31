package com.chrysanthemum.blog.service;


import com.chrysanthemum.blog.bean.AdminUser;

public interface AdminUserService {

    AdminUser login(String userName, String password);


    AdminUser getUserDetailById(Integer loginUserId);

    boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);

    boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
