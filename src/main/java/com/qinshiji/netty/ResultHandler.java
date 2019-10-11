package com.qinshiji.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 返回结果处理
 * @author qinshiji
 * @data 2019/7/30 11:25
 */
public class ResultHandler extends ChannelInboundHandlerAdapter {
    private Object response;

    /**
     * 监听读取事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.response = msg;
        ctx.close();
    }

    public Object getResponse() {
        return response;
    }
}
