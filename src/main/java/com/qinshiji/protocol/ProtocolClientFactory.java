package com.qinshiji.protocol;

import com.qinshiji.http.HttpClient;
import com.qinshiji.http.HttpServer;
import com.qinshiji.netty.NettyRpcClient;
import com.qinshiji.netty.NettyRpcServer;

/**
 * @author qinshiji
 * @date 2019/10/11 14:54
 */
public class ProtocolClientFactory {

    public static ProtocolClient getClient(String protocol){
            switch (protocol){
                case "dubbo":
                    return new NettyRpcClient();
                case "http":
                    return new HttpClient();
                default:
                    break;
            }
            return new HttpClient();
        }
}
