package permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @create: 2019-07-01 09:18
 * @author: Aner
 * @description:
 **/
public class BitAndWildPermissionResolver implements PermissionResolver {

    @Override
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("+")) {
            return (Permission) new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
