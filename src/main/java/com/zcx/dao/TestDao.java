package com.zcx.dao;

import com.zcx.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:50
 * @Description:
 */
@Repository("TestDao")
public interface TestDao {
    List<User> findAllUser();
}
