package com.qinshiji.consumer;


import com.qinshiji.entity.User;
import com.qinshiji.proxy.ProxyFactory;
import com.qinshiji.service.HelloService;

/**
 * 消费者
 * @author qinshiji
 * @date 2019/10/10 16:47
 */
public class Consumer {
    public static void main(String[] args) {
        HelloService helloService = ProxyFactory.getProxyClass(HelloService.class);
        String result = helloService.hello("秦世吉", 18);
        System.out.println(result);
    }
}
