package com.xcc.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create: 2019-07-04 09:12
 * @author: Aner
 * @description:
 **/
@WebServlet(name ="AuthenricatedServlet" ,urlPatterns = "/authenticated")
public class AuthenricatedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            req.getRequestDispatcher("/WEB-INF/jsp/authenricated.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }
    }
}
