package com.chrysanthemum.blog.service;


import com.chrysanthemum.blog.bean.AdminUser;

public interface AdminUserService {

    AdminUser login(String userName, String password);


}
