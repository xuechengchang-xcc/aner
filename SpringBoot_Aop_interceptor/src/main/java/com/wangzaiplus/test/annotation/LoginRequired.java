package com.wangzaiplus.test.annotation;

import java.lang.annotation.*;

/**
 * @date ：2020/2/19 23:21
 * @Author : 安儿
 * Description: TODO
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    /**
     * 是否需要登录，缺省为需要
     * @return
     */
    boolean loginRequired() default true;
}
