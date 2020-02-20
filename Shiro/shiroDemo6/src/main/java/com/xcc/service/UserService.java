package com.xcc.service;

import com.xcc.entity.User;

import java.util.Set;

/**
 * @create: 2019-07-03 10:14
 * @author: Aner
 * @description:
 **/
public interface UserService {
    /** 
    * @Author: Aner
    * @Description: 添加用户
    */ 
    public User createUser(User user);
    /** 
    * @Author: Aner
    * @Description: 修改密码
    */ 
    public void changePassword(Long userId,String newPassword);

    /**
     * @Author: Aner
     * @Description: 添加用户-角色关系
     */
    public  void correlationRoles(Long userId,Long...roleIds);

    /** 
    * @Author: Aner
    * @Description: 移除用户-角色关系
    */ 
    public  void uncorrelationRoles(Long userId,Long...roleIds);
    /** 
    * @Author: Aner
    * @Description: 根据用户名查找用户
    */ 
    public User findByUsername(String username);
    /** 
    * @Author: Aner
    * @Description: 根据用户名查找其角色
    */ 
    public Set<String> findRoles(String username);
    /** 
    * @Author: Aner
    * @Description: 根据用户名查找其权限
    */ 
    public Set<String> findPermissions(String username);
}
