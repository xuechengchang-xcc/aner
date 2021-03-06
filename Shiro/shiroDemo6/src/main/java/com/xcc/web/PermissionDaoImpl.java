package com.xcc.web;

import com.xcc.entity.Permission;
import com.xcc.utis.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @create: 2019-07-02 20:46
 * @author: Aner
 * @description:
 **/
public class PermissionDaoImpl implements  PermissionDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();
    /** 
    * @Author: Aner
    * @Description: 添加权限
    */ 
    @Override
    public Permission createPermission(Permission permission) {
        final String sql ="insert into sys_permissions(permission,description ,available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst =connection.prepareStatement(sql,new String[]{"id"});
                psst.setString(1,permission.getPermission());
                psst.setString(2,permission.getPermission());
                psst.setBoolean(3,permission.getAvailable());
                return psst;
            }
        },keyHolder);
        permission.setId(keyHolder.getKey().longValue());
        return permission;
    }

    @Override
    public void deletePermission(Long permissionId) {
            //首先把与permission关联的相关表的数据删掉
        String sql ="delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql,permissionId);

        sql="delete from sys_permissions where id=?";
        jdbcTemplate.update(sql,permissionId);
    }
}
