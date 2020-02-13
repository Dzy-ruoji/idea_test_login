package com.waxsb.service.impl;

import com.waxsb.dao.UserDao;
import com.waxsb.dao.impl.UserDaoImpl;
import com.waxsb.model.User;
import com.waxsb.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao= new UserDaoImpl();
    //注册用户
    @Override
    public boolean register(User user) {
        //1.根据用户名查询用户对象
        if(dao.findByUsername(user.getUsername())==null){
            //2.保存用户信息
            dao.save(user);
            return true;
        }

        return false;
    }

    @Override
    public User login(User user) {
        //根据用户名和密码判断登入是否成功
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void updateUser(User user) {
         dao.update(user);
    }

    @Override
    public void changePassword(User user, String newPassword) {
        dao.changePassword(user,newPassword);
    }
}
