package com.example.qingblog.service;

import com.example.qingblog.entity.Article;

import java.util.List;

public interface ArticleService {

    Article getById(String id);

    List<Article> list();

    /**
     * 根据分类名查询文章
     * @param categoryName
     * @return
     */
    List<Article> getArticlesByCategoryName(String categoryName);

    /**
     * 保存文章
     * @param article
     */
    void save(Article article);

    /**
     * 删除文章
     * @param id
     */
    void delete(String id);

    /**
     * 模糊查找文章
     * @param key
     * @return
     */
    List<Article> search(String key);
}
