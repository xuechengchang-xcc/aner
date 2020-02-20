package com.xcc.web;

import com.xcc.entity.Role;
import com.xcc.utis.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @create: 2019-07-02 21:04
 * @author: Aner
 * @description:
 **/
public class RoleDaoImpl implements  RoleDao{
    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public Role createRole(final  Role role) {
        final String sql="insert into sys_roles(role,description,available) values (?,?,?)";

        GeneratedKeyHolder keyHolder =new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement  psst =connection.prepareStatement(sql,new String[]{"id"});
                psst.setString(1,role.getRole());
                psst.setString(2,role.getDescription());
                psst.setBoolean(3,role.getAvailable());
                return psst;
            }
        },keyHolder);
        role.setId(keyHolder.getKey().longValue());
        return role;
    }

    @Override
    public void deleteRole(Long roleId) {
        //首先把和role关联的相关数据删掉
        String sql ="delete from sys_users_roles where role_id=?";
        jdbcTemplate.update(sql,roleId);

        sql="delete from sys_roles where id=?";
        jdbcTemplate.update(sql,roleId);

    }

    @Override
    public void correlationPermissions(Long roleId, Long...permissionIds) {
        if (permissionIds == null || permissionIds.length==0) {
            return;
        }
        String sql="insert into sys_roles_permissions(role_id,permission_id) values(?,?)";
        for (Long permissionId : permissionIds) {
            if (!exists(roleId,permissionId)){
                jdbcTemplate.update(sql,roleId,permissionId);
            }
        }
    }

    @Override
    public void uncorrelationPermissions(Long roleId, Long... permissiondIds) {
        if(permissiondIds ==null || permissiondIds.length==0){
                return ;
        }
        String sql="delete from sys_roles_permissions where role_id=? and permission_id=?";
        for (Long permissionId: permissiondIds){
            if (exists(roleId,permissionId)) {
                jdbcTemplate.update(sql,roleId,permissionId);
            }
        }
    }


    private Boolean exists(Long roleId,Long permissionId){
        String sql="select count(1) from sys_roles_permissions where role_id=? and permission_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,roleId,permissionId)!=0;
    }
}
