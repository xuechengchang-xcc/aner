package com.xcc.dao;

import com.xcc.eneity.User;

import java.util.Set;

/**
 * @create: 2019-07-08 19:19
 * @author: Aner
 * @description:
 **/
public interface UserDao {
    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId,Long...roleIds);
    public void uncorrelationRoles(Long userId,Long... roleIds);

    User findOne(Long userId);
    User findByUsername(String username);
    Set<String> findRoles(String username);
    Set<String> findPermissions(String username);
}
