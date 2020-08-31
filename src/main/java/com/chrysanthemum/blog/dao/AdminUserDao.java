package com.chrysanthemum.blog.dao;


import com.chrysanthemum.blog.bean.AdminUser;
import com.chrysanthemum.blog.bean.AdminUserExample;
import org.springframework.stereotype.Repository;

/**
 * AdminUserDao继承基类
 */
@Repository
public interface AdminUserDao extends MyBatisBaseDao<AdminUser, Integer, AdminUserExample> {
}