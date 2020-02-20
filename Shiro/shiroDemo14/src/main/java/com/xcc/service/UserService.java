package com.xcc.service;

import com.xcc.eneity.User;

import java.util.Set;

/**
 * @create: 2019-07-09 09:19
 * @author: Aner
 * @description:
 **/
public interface UserService {
    /** 
    * @Author: Aner
    * @Description: 创建用户
    */ 
    public User createUser(User user);
    /** 
    * @Author: Aner
    * @Description: 修改面膜
    */ 
    public void changePassword(Long userId, String newPassword);
    /**
    * @Author: Aner
    * @Description: 添加用户-角色关系
    */
    public void correlationRoles(Long userId, Long... roleIds);
    /**
    * @Author: Aner
    * @Description: 移除用户-角色关系
    */
    public void uncorrelationRoles(Long userId, Long... roleIds);
    /** 
    * @Author: Aner
    * @Description: 根据用户名查找用户
    */ 
    public User findByUsername(String username);
    /** 
    * @Author: Aner
    * @Description: 根据用户名查找角色
    */ 
    public Set<String> findRoles(String username);
    /** 
    * @Author: Aner
    * @Description: 根据用户名查找其权限 
    */ 
    public Set<String> findPermissions(String username);
}
