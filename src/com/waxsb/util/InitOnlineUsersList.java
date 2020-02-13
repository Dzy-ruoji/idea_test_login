package com.waxsb.util;

import com.waxsb.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

public class InitOnlineUsersList implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //在线登录用户列表
        List<User> userList = new ArrayList<User>();
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("userList",userList);
        System.out.println(userList==null);
        System.out.println("监听Servlet对象创建时触发");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("监听Servlet对象销毁时触发");
    }
}
