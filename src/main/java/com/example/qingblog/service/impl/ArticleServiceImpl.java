package com.example.qingblog.service.impl;

import com.example.qingblog.dao.ArticleDao;
import com.example.qingblog.entity.Article;
import com.example.qingblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;


    /**
     * MM表示月份，mm表示分钟
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public Optional<Article> getById(String id) {
        Optional<Article> article = articleDao.findById(id);
        return article;
    }

    @Override
    public List<Article> list() {
        return articleDao.findAll();
    }

    @Override
    public List<Article> getArticlesByCategoryName(String categoryName) {
        return articleDao.findAllByCategory_Name(categoryName);
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public void delete(String id) {
        articleDao.deleteById(id);
    }

    @Override
    public List<Article> search(String key) {
        return articleDao.findAllByTitleLike(key);
    }
}
