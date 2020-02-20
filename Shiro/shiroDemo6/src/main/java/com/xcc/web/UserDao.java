package com.xcc.web;

import com.xcc.entity.User;

import java.util.Set;

/**
 * @create: 2019-07-02 21:28
 * @author: Aner
 * @description:
 **/
public interface UserDao {
    public User createUser(User user);
    public void updateUser(User user);
    public void deleteUser(Long userId);

    public void correlationRoles(Long userId,Long...roleIds);
    public void uncorrelationRoles(Long userId,Long...roleIds);


    User findOne(Long userId);

    User findByUsername(String username);

    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);
}
