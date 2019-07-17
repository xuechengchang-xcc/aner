package com.xcc.web;

import com.xcc.entity.Role;

/**
 * @create: 2019-07-02 21:00
 * @author: Aner
 * @description:
 **/
public interface RoleDao {
    /** 
    * @Author: Aner
    * @Description: 添加角色
    */ 
    public Role createRole(Role role);
    /** 
    * @Author: Aner
    * @Description: 删除角色
    */ 
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId,Long...permissionIds);
    public void uncorrelationPermissions(Long roleId,Long...permissiondIds);
}

