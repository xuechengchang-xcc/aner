package com.xcc.service;

import com.xcc.web.RoleDao;
import com.xcc.web.RoleDaoImpl;
import com.xcc.entity.Role;

/**
 * @create: 2019-07-03 09:22
 * @author: Aner
 * @description:
 **/
public class RoleServiceImpl implements RoleService{

    private RoleDao roleDao =new RoleDaoImpl();

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }
    /** 
    * @Author: Aner
    * @Description: 添加角色-权限之间的关系
    */ 
    @Override
    public void correlationPermissions(Long roleId, Long... permissionIds) {
            roleDao.correlationPermissions(roleId,permissionIds);
    }
    /** 
    * @Author: Aner
    * @Description: 移除角色-权限之间关系 
    */ 
    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
            roleDao.uncorrelationPermissions(roleId,permissionIds
            );
    }
}
