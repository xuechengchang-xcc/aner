package com.xcc.service;

import com.xcc.web.PermissionDao;
import com.xcc.web.PermissionDaoImpl;
import com.xcc.entity.Permission;

/**
 * @create: 2019-07-03 09:17
 * @author: Aner
 * @description: 权限实现类
 **/
public class PermissionServiceImpl implements PermissionService{

    private PermissionDao permissionDao =new PermissionDaoImpl();

    @Override
    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
