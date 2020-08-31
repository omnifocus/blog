package com.chrysanthemum.blog.dao;

import com.chrysanthemum.blog.bean.Category;
import com.chrysanthemum.blog.bean.CategoryExample;
import org.springframework.stereotype.Repository;

/**
 * CategoryDao继承基类
 */
@Repository
public interface CategoryDao extends MyBatisBaseDao<Category, Integer, CategoryExample> {
}