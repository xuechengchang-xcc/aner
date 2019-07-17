package com.xcc.web;

import com.xcc.entity.Permission;

/**
 * @create: 2019-07-02 20:44
 * @author: Aner
 * @description:
 **/
public interface PermissionDao {
    /** 
    * @Author: Aner
    * @Description: 添加权限
    */ 
     public Permission createPermission(Permission permission);

     public void deletePermission(Long permissionId);
}
