package com.example.qingblog.dao;

import com.example.qingblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, String> {

    @Query("from User u where u.username = :username and u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
