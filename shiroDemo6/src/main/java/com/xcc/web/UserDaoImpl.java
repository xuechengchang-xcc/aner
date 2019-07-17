package com.xcc.web;

import com.xcc.entity.User;
import com.xcc.utis.JdbcTemplateUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @create: 2019-07-02 21:32
 * @author: Aner
 * @description:
 **/
public class UserDaoImpl implements UserDao{

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

    @Override
    public User createUser(final User user) {
        final String sql ="insert into sys_users(username,password,salt,locked) values(?,?,?,?)";

        GeneratedKeyHolder keyHolder=  new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst =connection.prepareStatement(sql,new String[]{"id"});
                psst.setString(1,user.getUsername());
                psst.setString(2,user.getPassword());
                psst.setString(3,user.getSalt());
                psst.setBoolean(4,user.getLocked());
                return psst;
            }
        },keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public void updateUser(User user) {
        String sql ="update sys_users set username=?,password=?,salt=?,locked? where id=?";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked(),user.getId());
    }

    @Override
    public void deleteUser(Long userId) {
        String sql="delete from sys_users where id=?";
        jdbcTemplate.update(sql,userId);
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if(roleIds ==null || roleIds.length ==0){
            return ;
        }
        String sql="insert into sys_users_roles(user_id,role_id) values(?,?)";
        for (Long roleId : roleIds) {
            if(!exists(userId,roleId)){
                jdbcTemplate.update(sql,userId,roleId);
            }
        }
    }

    public boolean exists(Long userId,Long roleId){
        String sql ="select count(1) from sys_users_roles where user_id=? and role_id=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,userId,roleId) !=0;
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if (roleIds ==null || roleIds.length==0){
            return ;
        }
        String sql="delete from sys_users_roles where user_id=? and role_id=?";
        for (Long roleId: roleIds){
            if (exists(userId,roleId)){
                jdbcTemplate.update(sql,userId,roleId);
            }
        }
    }

    @Override
    public User findOne(Long userId) {
        String sql="select id,username,password,salt,locked from sys_users where id=?";
        List<User> userList =jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class),userId);
        if (userList.size()==0){
            return null;
        }
        return userList.get(0);
    }

    @Override
    public User findByUsername(String username) {
        String sql="select id,username,password,salt,locked from sys_users where username=?";
        List<User> userList =jdbcTemplate.query(sql,new BeanPropertyRowMapper(User.class),username);
        if (userList.size()==0) {
            return null;
        }
        return userList.get(0);
    }

    @Override
    public Set<String> findRoles(String username) {
        String sql="SELECT r.`role` FROM sys_roles r ,sys_users u,sys_users_roles ur " +
                "WHERE u.`id`=ur.`user_id` AND r.`id` =ur.`role_id` AND u.`username`=?";
        return new HashSet(jdbcTemplate.queryForList(sql,String.class,username));
    }

    @Override
    public Set<String> findPermissions(String username) {
        String sql="SELECT p.`permission` FROM sys_permissions p ,sys_roles r,sys_roles_permissions rp \n" +
                "WHERE p.`id` =rp.`permission_id` AND r.`id` =rp.`role_id` AND r.`id` IN(SELECT r.`id` FROM sys_roles r ,sys_users u,sys_users_roles ur \n" +
                "WHERE u.`id`=ur.`user_id` AND r.`id` =ur.`role_id` \n" +
                "AND u.`username`=?\n" +
                ")\n";
        return new HashSet(jdbcTemplate.queryForList(sql,String.class,username));
    }
}
