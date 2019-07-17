package com.xcc.dao;

import com.xcc.eneity.Permission;
import org.springframework.stereotype.Repository;

/**
 * @create: 2019-07-08 18:24
 * @author: Aner
 * @description:
 **/
public interface PermissionDao {

    Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);
}
