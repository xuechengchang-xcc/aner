package com.wangzaiplus.test.controller;

import com.wangzaiplus.test.common.ServerResponse;
import com.wangzaiplus.test.pojo.User;
import com.wangzaiplus.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public ServerResponse getAll() {
        List<User> users = userService.getAll();
        return ServerResponse.success(users);
    }

    @GetMapping("{id}")
    public ServerResponse getOne(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (null != user) {
            return ServerResponse.success(user);
        } else {
            return ServerResponse.error("not exists");
        }
    }

    @PostMapping
    public ServerResponse add(User user) {
        userService.add(user);
        return ServerResponse.error("nice");
    }

    @PutMapping
    public ServerResponse update(User user) {
        userService.update(user);
        return ServerResponse.error("nice");
    }

    @DeleteMapping("{id}")
    public ServerResponse delete(@PathVariable Integer id) {
        userService.delete(id);
        return ServerResponse.error("nice");
    }

        @PostMapping("login")
    public ServerResponse login(String username, String password) {
        userService.login(username, password);
        return ServerResponse.success();
    }

}
