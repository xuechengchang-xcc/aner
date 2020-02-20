package com.xcc.service;

import com.xcc.entity.Permission;

/**
 * @create: 2019-07-03 09:12
 * @author: Aner
 * @description: 权限
 **/
public interface PermissionService {
    /** 
    * @Author: Aner
    * @Description: 添加权限
    */ 
    public Permission createPermission(Permission permission);
    /** 
    * @Author: Aner
    * @Description: 删除权限
    */ 
    public void deletePermission(Long permissionId);
}
