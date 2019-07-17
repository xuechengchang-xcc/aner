package com.xcc.web;

import org.apache.shiro.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create: 2019-07-03 20:55
 * @author: Aner
 * @description:
 **/
@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SecurityUtils.getSubject().logout();
        req.getRequestDispatcher("/WEB-INF/jsp/loginoutSuccess.jsp").forward(req,resp);
    }
}
