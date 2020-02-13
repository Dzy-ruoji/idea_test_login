package com.waxsb.contorller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer size = (Integer) request.getSession().getServletContext().getAttribute("size");
        request.getSession().getServletContext().setAttribute("size",size-1);
        //销毁session
        request.getSession().invalidate();
        //跳转登录页面
        response.sendRedirect(request.getContextPath()+"/html/login.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
