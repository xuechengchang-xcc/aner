package com.xcc.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @create: 2019-07-04 12:14
 * @author: Aner
 * @description:
 **/
@WebServlet(name ="UnAuthorizedServlet",urlPatterns = "/unauthorized")
public class UnAuthorizedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/unauthorized.jsp").forward(req,resp);
    }
}
