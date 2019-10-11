package com.qinshiji.service;

import com.qinshiji.entity.User;

/**
 * @author qinshiji
 * @date 2019/10/10 17:26
 */
public class HelloServiceImpl implements HelloService{

    @Override
    public String hello(String name, Integer age) {
        return "hello:"+name+"age:"+age;
    }
}
