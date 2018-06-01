package com.example.qingblog.service.impl;

import com.example.qingblog.dao.UserDao;
import com.example.qingblog.entity.User;
import com.example.qingblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final
    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 如果在数据库找到对应记录登录成功，否则失败
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username,password);
        if (null != user){
            return true;
        }else {
            return false;
        }
    }
}
