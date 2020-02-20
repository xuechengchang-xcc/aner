package com.xcc.service;

import com.xcc.dao.PermissionDao;
import com.xcc.dao.PermissionDaoImpl;
import com.xcc.eneity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @create: 2019-07-09 09:12
 * @author: Aner
 * @description:
 **/

public class PermissionServiceImpl implements PermissionService{

    private PermissionDao permissionDao;

    public PermissionDao getPermissionDao() {
        return permissionDao;
    }

    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    public Permission createPermission(Permission permisson){
        return permissionDao.createPermission(permisson);
    }

    public void deletePermission(Long permissionId){
        permissionDao.deletePermission(permissionId);
    }
}
