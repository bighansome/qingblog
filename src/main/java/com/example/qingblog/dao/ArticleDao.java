package com.example.qingblog.dao;

import com.example.qingblog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article, String> {

    List<Article> findAllByCategory_Name(String name);

    @Query("from Article where title like %:title%")
    List<Article> findAllByTitleLike(@Param("title") String title);
}
