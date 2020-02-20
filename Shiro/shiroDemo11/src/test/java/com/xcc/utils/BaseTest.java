package com.xcc.utils;

import com.xcc.eneity.Permission;
import com.xcc.eneity.Role;
import com.xcc.eneity.User;
import com.xcc.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;

/**
 * @create: 2019-07-09 12:02
 * @author: Aner
 * @description:
 **/
public abstract class BaseTest {
    protected PermissionService permissionService =new PermissionServiceImpl();
    protected RoleService roleService =new RoleServiceImpl();
    protected UserService userService =new UserServiceImpl();

    protected  String password ="123";

    protected Permission p1;
    protected Permission p2;
    protected Permission p3;
    protected Role r1;
    protected Role r2;
    protected User u1;

    @Before
    public void setUp(){
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_permissions");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_users_roles");
        JdbcTemplateUtils.jdbcTemplate().update("delete from sys_roles_permissions");

        //新增权限
        p1 =new Permission("user:create","用户模块新增",Boolean.TRUE);
        p2=new Permission("user:update","用户模块修改",Boolean.TRUE);
        p3=new Permission("menu:create","菜单模块新增",Boolean.TRUE);

        permissionService.createPermission(p1);
        permissionService.createPermission(p2);
        permissionService.createPermission(p3);

        //新增角色
        r1=new Role("admin","管理员",Boolean.TRUE);
        r2=new Role("user","用户管理员",Boolean.TRUE);

        roleService.createRole(r1);
        roleService.createRole(r2);

        //关联角色-权限(给r1角色添加三个权限)
        roleService.correlationPermissions(r1.getId(),p1.getId());
        roleService.correlationPermissions(r1.getId(),p2.getId());
        roleService.correlationPermissions(r1.getId(),p3.getId());

        roleService.correlationPermissions(r2.getId(),p1.getId());
        roleService.correlationPermissions(r2.getId(),p2.getId());

        //新增用户
        u1=new User("zhang",password);
        userService.createUser(u1);

        //关联新增的用户和角色
        userService.correlationRoles(u1.getId(),r1.getId());
        /*用户和角色和权限添加完毕*/

        //获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =new IniSecurityManagerFactory("classpath:shiro.ini");

        //得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    @After
    public void tearDown() throws Exception{
        ThreadContext.unbindSubject();  //退出清楚绑定Subject到线程。后续会有影响
    }
    protected  void login(String username,String password){
        //的到Subject，并创建信息验证Token
        Subject subject =SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);
    }
    public  Subject subject(){
        return SecurityUtils.getSubject();
    }
}
