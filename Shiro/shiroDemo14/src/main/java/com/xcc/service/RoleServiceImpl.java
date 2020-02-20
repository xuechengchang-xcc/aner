package com.xcc.service;

import com.xcc.dao.RoleDao;
import com.xcc.eneity.Role;

/**
 * @create: 2019-07-09 09:17
 * @author: Aner
 * @description:
 **/

public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(Role role) {
        return  roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
        roleDao.correlationPermissions(roleId,permissionIds);
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
        roleDao.uncorrelatioinPermissions(roleId,permissionIds);
    }
}
