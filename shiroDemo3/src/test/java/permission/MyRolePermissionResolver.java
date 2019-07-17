package permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * @create: 2019-07-01 09:39
 * @author: Aner
 * @description:
 **/
public class MyRolePermissionResolver implements RolePermissionResolver {
     @Override
    public Collection<Permission> resolvePermissionsInRole(String s) {
        if ("role1".equals(s)){
            return Arrays.asList(new WildcardPermission("menu:*"));
        }
        return null;
    }
}
