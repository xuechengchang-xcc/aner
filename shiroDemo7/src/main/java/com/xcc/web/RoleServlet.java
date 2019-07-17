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
 * @create: 2019-07-04 09:58
 * @author: Aner
 * @description:
 **/
@WebServlet(name = "RoleServlet" ,urlPatterns = "/role")
public class RoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject =SecurityUtils.getSubject();
        subject.checkRole("admin");
        req.setAttribute("subject",subject);
        req.getRequestDispatcher("/WEB-INF/jsp/hasRole.jsp").forward(req,resp);
    }
}
