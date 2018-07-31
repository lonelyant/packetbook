package com.zcx.service.impl;

import com.zcx.dao.TestDao;
import com.zcx.model.User;
import com.zcx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:50
 * @Description:
 */
@Service("TestService")
public class TestServiceImpl implements TestService{

    @Autowired
    private TestDao testDao;

    @Override
    public List<User> findAllUser() {
        return testDao.findAllUser();
    }
}
