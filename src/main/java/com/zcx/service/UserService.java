package com.zcx.service;

import com.zcx.model.User;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:50
 * @Description:
 */
public interface UserService {
    List<User> findAllUser();
    void updateUser(User user);
    User findById(String id);
}
