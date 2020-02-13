package com.waxsb.dao;

import com.waxsb.model.User;

public interface UserDao {
    //根据用户名查询用户信息
    public User findByUsername(String username);
    //用户保存
    public void save(User user);
    //根据用户名和密码判断是否有该用户
    public User findUserByUsernameAndPassword(String username,String password);
    //更新user
    void update(User user);

    void changePassword(User user, String newPassword);
}
