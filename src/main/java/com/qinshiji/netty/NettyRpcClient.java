package com.qinshiji.netty;

import com.qinshiji.entity.Invocation;
import com.qinshiji.protocol.ProtocolClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * nettyRpc客户端
 *
 * @author qinshiji
 * @data 2019/7/30 11:11
 */
public class NettyRpcClient implements ProtocolClient {
    @Override
    public  String send(String hostname, Integer port, Invocation invocation) {
                //        主线程池
                NioEventLoopGroup mainGrp = new NioEventLoopGroup();
                final ResultHandler resultHandler = new ResultHandler();
                try {
//        创建Netty服务器的启动对象
                    Bootstrap bootstrap = new Bootstrap();
//        初始化启动对象
                    bootstrap
                            //                指定线程池
                            .group(mainGrp)
                            //                指定Netty通道类型
                            .channel(NioSocketChannel.class)
                            //                指定通道初始化器,用来加载当收到channel消息后如何进行业务处理
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    //        获取管道
                                    ChannelPipeline pipeline = socketChannel.pipeline();
//                          添加编码器
                                    pipeline.addLast("encoder", new ObjectEncoder());
//                          添加解码器
                                    pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
//                          自定义调用处理
                                    pipeline.addLast(resultHandler);
                                }
                            });
//        绑定服务器,以同步的方式启动服务器
                    ChannelFuture channelFuture = bootstrap.connect(hostname, port).sync();
                    channelFuture.channel().writeAndFlush(invocation);
//            等待服务器关闭
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//            优雅的关闭线程池
                    mainGrp.shutdownGracefully();
                }
//                返回服务器返回的数据
                return (String) resultHandler.getResponse();
            }
}
