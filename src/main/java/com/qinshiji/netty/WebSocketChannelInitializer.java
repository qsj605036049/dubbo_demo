package com.qinshiji.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author qinshiji
 * @data 2019/7/30 10:27
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //        获取管道
        ChannelPipeline pipeline = socketChannel.pipeline();
//        添加编码器
        pipeline.addLast("encoder",new ObjectEncoder());
//        添加解码器
        pipeline.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
//        自定义调用处理
        pipeline.addLast(new InvokeHandler());
    }
}
