package com.xcc.service;

import com.xcc.dao.UserDao;
import com.xcc.eneity.User;

import java.util.Set;

/**
 * @create: 2019-07-09 09:59
 * @author: Aner
 * @description:
 **/
public class UserServiceImpl implements UserService{
    private UserDao userDao;
    private PasswordHelper passwordHelper;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public PasswordHelper getPasswordHelper() {
        return passwordHelper;
    }

    public void setPasswordHelper(PasswordHelper passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    /**
    * @Author: Aner
    * @Description: 创建用户
    */ 
    @Override
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }
    /** 
    * @Author: Aner
    * @Description: 修改密码
    */ 
    @Override
    public void changePassword(Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }
    /** 
    * @Author: Aner
    * @Description: 添加用户-角色关系
    */ 
    public void correlationRoles(Long userId ,Long...roleIds){
        userDao.correlationRoles(userId, roleIds);
    }
    /**
    * @Author: Aner
    * @Description: 移除用户-角色关系
    */
    public void uncorrelationRoles(Long userId,Long... roleIds){
        userDao.uncorrelationRoles(userId, roleIds);
    }
    /** 
    * @Author: Aner
    * @Description: 根据用户名查找用户
    */ 
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    /** 
    * @Author: Aner
    * @Description: 根据用户名查找角色
    */ 
    @Override
    public Set<String> findRoles(String username) {
        return userDao.findRoles(username);
    }
    /**
    * @Author: Aner
    * @Description: 根据用户名查找权限
    */
    @Override
    public Set<String> findPermissions(String username) {
        return userDao.findPermissions(username);
    }
}
