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
 * @create: 2019-07-04 12:15
 * @author: Aner
 * @description:
 **/
@WebServlet(name = "PermissionServlet",urlPatterns = "/permission")
public class PermissionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject =SecurityUtils.getSubject();
        subject.checkPermission("user:create");
        req.setAttribute("subject",subject);
        req.getRequestDispatcher("/WEB-INF/jsp/hasPermission.jsp").forward(req,resp);
    }
}
