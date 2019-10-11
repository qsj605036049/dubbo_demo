package com.qinshiji.protocol;

import com.qinshiji.http.HttpServer;
import com.qinshiji.netty.NettyRpcServer;

/**
 * 协议工厂
 * @author qinshiji
 * @date 2019/10/10 18:18
 */
public class ProtocolServerFactory {
    public static ProtocolServer getServer(String protocol){
        switch (protocol){
            case "dubbo":
                return new NettyRpcServer();
            case "http":
                return new HttpServer();
            default:
                break;
        }
        return new HttpServer();
    }
}
