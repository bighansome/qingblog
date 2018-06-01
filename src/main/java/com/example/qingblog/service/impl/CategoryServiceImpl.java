package com.example.qingblog.service.impl;

import com.example.qingblog.dao.CategoryDao;
import com.example.qingblog.entity.Category;
import com.example.qingblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final
    CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> list() {
        return categoryDao.findAll();
    }

    /**
     * Optional 怎么用
     * @param id
     * @return
     */
    @Override
    public Optional<Category> get(String id) {
        if (id == null || id.length() == 0){
            return null;
        }
        return categoryDao.findById(id);
    }

    @Override
    public Category getByName(String name) {
        if (name == null || name.length() == 0){
            return null;
        }
        return categoryDao.findByName(name);
    }
}
