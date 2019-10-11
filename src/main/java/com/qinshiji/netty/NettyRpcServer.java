package com.qinshiji.netty;

import com.qinshiji.protocol.ProtocolServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author qinshiji
 * @data 2019/7/30 10:25
 */
public class NettyRpcServer implements ProtocolServer {
    /**
     * rpc启动方法
     */
    @Override
    public void start(String hostname, Integer port) {
        //        主线程池
        NioEventLoopGroup mainGrp = new NioEventLoopGroup();
//        从线程池
        NioEventLoopGroup subGrp = new NioEventLoopGroup();
        try {
//        创建Netty服务器的启动对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();
//        初始化启动对象
            serverBootstrap
                    //                指定线程池
                    .group(mainGrp, subGrp)
                    //                指定Netty通道类型
                    .channel(NioServerSocketChannel.class)
                    //                指定通道初始化器,用来加载当收到channel消息后如何进行业务处理
                    .childHandler(new WebSocketChannelInitializer());
//        绑定服务器,以同步的方式启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
//            等待服务器关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            优雅的关闭线程池
            mainGrp.shutdownGracefully();
            subGrp.shutdownGracefully();
        }
    }

}
