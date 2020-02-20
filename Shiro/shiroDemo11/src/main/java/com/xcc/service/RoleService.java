package com.xcc.service;

import com.xcc.eneity.Role;

/**
 * @create: 2019-07-09 09:14
 * @author: Aner
 * @description:
 **/
public interface RoleService {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    /** 
    * @Author: Aner
    * @Description: 添加角色-权限之间关系
    */ 
    public void correlationPermissions(Long roleId,Long... permissionIds);
    /** 
    * @Author: Aner
    * @Description: 移除角色-权限之间关系
    */ 
    public void uncorrelationPermissions(Long roleId,Long... permissionIds);
}
