package com.xcc.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @create: 2019-07-05 10:23
 * @author: Aner
 * @description:
 **/
public class MySessionListener1 implements SessionListener {
    @Override
    public void onStart(Session session) { //会话创建时触发
        System.out.println("会话创建："+session.getId());
    }

    @Override
    public void onExpiration(Session session) { //会话过期触发
        System.out.println("会话过期："+session.getId());
    }

    @Override
    public void onStop(Session session) { //退出/会话过期时触发
        System.out.println("会话停止："+session.getId());
    }
}
