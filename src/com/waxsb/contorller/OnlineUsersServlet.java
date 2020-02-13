package com.waxsb.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waxsb.model.User;
import com.waxsb.util.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/onlineUsersServlet")
public class OnlineUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object size = request.getSession().getServletContext().getAttribute("size");
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;character=utf-8");
        User user = (User) request.getSession().getAttribute("user");
        ResultInfo resultInfo = new ResultInfo();
        if(user==null){
            resultInfo.setFlag(false);
        }else{
            resultInfo.setFlag(true);
        }
        resultInfo.setData(size);

        mapper.writeValue(response.getOutputStream(),resultInfo);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
