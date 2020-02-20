package com.xcc.dao;

import com.xcc.eneity.Permission;
import com.xcc.utils.JdbcTemplateUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @create: 2019-07-08 18:30
 * @author: Aner
 * @description:
 **/
public class PermissionDaoImpl implements PermissionDao {
    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public Permission createPermission(Permission permission) {
        final  String sql="insert into sys_permissions(permission,description,available) values(?,?,?)";

        GeneratedKeyHolder keyHolder =new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst =connection.prepareStatement(sql,new String[]{"id"});
                psst.setString(1,permission.getPermission());
                psst.setString(2,permission.getDescription());
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
        String sql ="delete from sys_roles_permission where permission_id=?";
        jdbcTemplate.update(sql,permissionId);

        sql="delete from sys_permissions where id=?";
        jdbcTemplate.update(sql,permissionId);
    }
}
