package com.xcc.service;

import com.xcc.eneity.Permission;

/**
 * @create: 2019-07-09 09:08
 * @author: Aner
 * @description:
 **/
public interface PermissionService {
    public Permission createPermission(Permission permission);
    public void deletePermission(Long permissionId);
}
