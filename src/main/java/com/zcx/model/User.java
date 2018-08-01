package com.zcx.model;

/**
 * @author: Ant
 * @Date: 2018/07/31 14:58
 * @Description:
 */
public class User {

    private String user_id;
    private String username;
    private String password;
    private double rich;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRich() {
        return rich;
    }

    public void setRich(double rich) {
        this.rich = rich;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rich=" + rich +
                '}';
    }
}
