package com.xcc.service;

import com.xcc.dao.PermissionDao;
import com.xcc.dao.PermissionDaoImpl;
import com.xcc.eneity.Permission;

/**
 * @create: 2019-07-09 09:12
 * @author: Aner
 * @description:
 **/
public class PermissionServiceImpl implements PermissionService{
    private PermissionDao permissionDao =new PermissionDaoImpl();

    public Permission createPermission(Permission permisson){
        return permissionDao.createPermission(permisson);
    }

    public void deletePermission(Long permissionId){
        permissionDao.deletePermission(permissionId);
    }
}
