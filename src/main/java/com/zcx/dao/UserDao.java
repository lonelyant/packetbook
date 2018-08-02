package com.zcx.dao;

import com.zcx.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:50
 * @Description:
 */
@Repository("TestDao")
public interface UserDao {
    List<User> findAllUser();
    void updateUser(User user);
    User findById(@Param("id") String id);
}
