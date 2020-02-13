package com.waxsb.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waxsb.model.User;
import com.waxsb.service.UserService;
import com.waxsb.service.impl.UserServiceImpl;
import com.waxsb.util.LockHelper;
import com.waxsb.util.OnlineUsers;
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
import java.util.List;
import java.util.Map;

@WebServlet("/html/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = (List<User>) request.getSession().getServletContext().getAttribute("userList");//获取已登录的用户
        String checkcode = request.getParameter("checkcode");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server =(String) session.getAttribute("CHECKCODE_SERVER");
        //比较，返回的时候验证码为空，会报异常
        if(!checkcode_server.equalsIgnoreCase(checkcode)){

            ResultInfo resultInfo = new ResultInfo();
            ObjectMapper mapper = new ObjectMapper();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
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
        //3.调用service完成登录
        UserService userService = new UserServiceImpl();
        User u=userService.login(user);
        ResultInfo resultInfo = new ResultInfo();
        if(u==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误");
        }

        if(u!=null){
            for(User userLogin : userList) {
                if(u.getUsername().equals(userLogin.getUsername())){
                    LockHelper.destroyedSession(userLogin);
                    break;
                }
            }
        }

        if(u!=null){
            //登录成功
            HttpSession session1 = request.getSession();
            session1.setAttribute("user",u);
            OnlineUsers onlineUsers = new OnlineUsers();
            onlineUsers.setUser(u);
            session1.setAttribute("onlineUsers",onlineUsers);//感知到自己被绑定，触发绑定事件
            LockHelper.putSession(session1);
            userList = (List<User>) session.getServletContext().getAttribute("userList");
            int size = userList.size();
            session.getServletContext().setAttribute("size",size);
            resultInfo.setFlag(true);
        }

        //将resultInfo对象序列化为json
        ObjectMapper mapper = new ObjectMapper();

        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;character=utf-8");
        mapper.writeValue(response.getOutputStream(),resultInfo);

    }







    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
