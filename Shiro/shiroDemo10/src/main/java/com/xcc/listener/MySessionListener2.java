package com.xcc.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * @create: 2019-07-05 10:23
 * @author: Aner
 * @description:
 **/
public class MySessionListener2 extends SessionListenerAdapter {
    @Override
    public void onStart(Session session) { //会话创建时触发
        System.out.println("会话创建："+session.getId());
    }
}
