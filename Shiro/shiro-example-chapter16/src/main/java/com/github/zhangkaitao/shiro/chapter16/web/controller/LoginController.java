package com.github.zhangkaitao.shiro.chapter16.web.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @create: 2019-07-15 16:49
 * @author: Aner
 * @description:
 **/
@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest request, Model model){
        String exceptionClassName=(String)request.getAttribute("shiroLoginFailure");
        String error=null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)){
            error="用户名/密码错误";
        }else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)){
            error="用户名/密码错误";
        }else if(exceptionClassName !=null){
            error ="其他错误："+exceptionClassName;
        }
        model.addAttribute("error",error);
       return "login";
    }
}
