package com.waxsb.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waxsb.model.User;
import com.waxsb.service.UserService;
import com.waxsb.service.impl.UserServiceImpl;
import com.waxsb.util.ResultInfo;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/html/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
               e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService userService = new UserServiceImpl();
        userService.updateUser(user);
        request.getSession().setAttribute("user",user);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;character=utf-8");
        mapper.writeValue(response.getOutputStream(),resultInfo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
