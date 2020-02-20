package com.xcc.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @create: 2019-07-05 10:27
 * @author: Aner
 * @description:
 **/
public class JdbcTemplateUtils {
    private static JdbcTemplate jdbcTemplate;

    public static  JdbcTemplate jdbcTemplate(){
        if (jdbcTemplate()==null){
            jdbcTemplate=createJdbcTemplate();
        }
        return jdbcTemplate();
    }

    private static JdbcTemplate createJdbcTemplate() {
        DruidDataSource ds =new DruidDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("root");
        ds.setPassword("root");

        return new JdbcTemplate(ds);
    }
}
