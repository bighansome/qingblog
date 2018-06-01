package com.example.qingblog.service;

import com.example.qingblog.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public List<Category> list();

    public Optional<Category> get(String id);

    public Category getByName(String name);
}
