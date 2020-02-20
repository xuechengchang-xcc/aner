package com.xcc.dao;

import com.xcc.eneity.Role;

/**
 * @create: 2019-07-08 18:37
 * @author: Aner
 * @description:
 **/
public interface RoleDao {
    public Role createRole(Role role);
    public void deleteRole(Long roleId);

    public void correlationPermissions(Long roleId,Long... permissionIds);
    public void uncorrelatioinPermissions(Long roleId,Long... permissionIds);
}
