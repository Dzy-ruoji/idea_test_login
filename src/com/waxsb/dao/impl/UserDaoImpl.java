package com.waxsb.dao.impl;

import com.waxsb.dao.UserDao;
import com.waxsb.model.User;
import com.waxsb.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;


public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public User findByUsername(String username) {
    //定义sql
     String sql="select* from user where username= ? ";
     //执行sql
        User user = null;
        try {
            List<Map<String, Object>> list = template.queryForList(sql,username);
            int size = list.size();
            if(size==0){
                return null;
            }
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);//查不到有异常
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public void save(User user) {
    //1.定义sql
        String sql="insert into user value(null,?,?,?,?,?,?,?)" ;
        System.out.println(user.getTelephone());
        template.update(sql,user.getName(),user.getGender(),user.getBirthday(),user.getTelephone(),user.getEmail(),user.getUsername(),
        user.getPassword());
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user=null;
        try {
            String sql = "select * from user where username= ? and password =?";
             user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    @Override
    public void update(User user) {
        String sql="update user set `name`=?,gender=?,birthday=?,telephone=?,email=?where username=?";
        template.update(sql,user.getName(),user.getGender(),user.getBirthday(),user.getTelephone(),user.getEmail(),user.getUsername());

    }

    @Override
    public void changePassword(User user, String newPassword) {
        String sql="update user set password=? where username=?";
        template.update(sql,newPassword,user.getUsername());
    }
}
