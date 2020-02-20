package com.wangzaiplus.test.controller;

import com.wangzaiplus.test.common.ServerResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redisson")
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("testRedisson")
    public ServerResponse testRedisson() {
        RLock lock = redissonClient.getLock("testRedissonLock");
        boolean locked = false;
        try {
            locked = lock.tryLock(0, 10, TimeUnit.SECONDS);
            if (locked) {
                Thread.sleep(3000);
                return ServerResponse.success("ok.......................................");
            } else {
                return ServerResponse.error("获取锁失败.......................................");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ServerResponse.error("获取锁异常.......................................");
        } finally {
            if (!locked) {
                return ServerResponse.error("获取锁失败");
            }
            lock.unlock();
        }
    }
}
