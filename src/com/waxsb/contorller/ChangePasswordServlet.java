package com.waxsb.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waxsb.model.User;
import com.waxsb.service.UserService;
import com.waxsb.service.impl.UserServiceImpl;
import com.waxsb.util.ResultInfo;
import org.omg.CORBA.portable.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static jdk.nashorn.internal.objects.Global.exit;

@WebServlet("/html/changePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录信息
       User user = (User) request.getSession().getAttribute("user");
        String prePassword = request.getParameter("prePassword");//旧密码
        String newPassword = request.getParameter("newPassword");//新密码
        String newPassword1 = request.getParameter("newPassword2");//确认新密码
        ResultInfo resultInfo = new ResultInfo();
        if(prePassword.equals(user.getPassword())&&newPassword.equals(newPassword1)){
            //调用sercivce层
            UserService userService = new UserServiceImpl();
            userService.changePassword(user,newPassword);
            request.getSession().setAttribute("user",user);
            resultInfo.setFlag(true);

        }else{
            if(!prePassword.equals(user.getPassword())){
                resultInfo.setErrorMsg("修改失败,原密码错误");
            }if(!newPassword.equals(newPassword1)){
                resultInfo.setErrorMsg("修改失败,新密码确认错误");
            }

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
