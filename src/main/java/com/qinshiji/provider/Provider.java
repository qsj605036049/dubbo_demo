package com.qinshiji.provider;

import com.qinshiji.constant.Constant;
import com.qinshiji.http.HttpServer;
import com.qinshiji.protocol.ProtocolServerFactory;
import com.qinshiji.registry.LocalRegister;
import com.qinshiji.registry.RemoteRegister;
import com.qinshiji.entity.URL;
import com.qinshiji.service.HelloService;
import com.qinshiji.service.HelloServiceImpl;

/**
 * 生产者
 *
 * @author qinshiji
 * @date 2019/10/10 16:48
 */
public class Provider {
    public static void main(String[] args) {
//        本地注册
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
//        注册中心注册
        URL url = new URL("127.0.0.1", 9001);
        RemoteRegister.register(HelloService.class.getName(), url);
//        tomcat启动
        ProtocolServerFactory.getServer(Constant.PROTOCOL).start(url.getHostname(), url.getPort());
    }
}
