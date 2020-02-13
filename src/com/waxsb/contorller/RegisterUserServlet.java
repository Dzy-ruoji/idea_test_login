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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


@WebServlet("/html/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        String checkcode = request.getParameter("checkcode");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");
        //session.removeAttribute("CHECKCODE_SERVER");//保证验证码只能使用一次
        //比较
        if(!checkcode_server.equalsIgnoreCase(checkcode)){
            //验证码错误
            ResultInfo resultInfo = new ResultInfo();
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultInfo);
            response.setContentType("application/json;character=utf-8");
            response.getWriter().write(json);
            return;
        }
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
        //3.调用service完成注册
        UserService userService = new UserServiceImpl();


        boolean flag =userService.register(user);
        ResultInfo resultInfo = new ResultInfo();
        //4.响应结果
        if(flag){
            //登录成功
            resultInfo.setFlag(true);
        }else{
            //注册失败
            resultInfo.setErrorMsg("注册失败,用户名相同");
        }
    //将resultInfo对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resultInfo);
        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;character=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
