package com.zcx.service.impl;

import com.zcx.dao.UserDao;
import com.zcx.model.User;
import com.zcx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:50
 * @Description:
 */
@Service("TestService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public void updateUser(User user) {
        double rich = user.getRich();
        DecimalFormat df = new DecimalFormat("######0.00");
        String s = df.format(rich);
        user.setRich(Double.parseDouble(s));
        userDao.updateUser(user);
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id);
    }
}
