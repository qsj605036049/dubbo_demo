package com.qinshiji.netty;

import com.alibaba.fastjson.JSON;
import com.qinshiji.entity.Invocation;
import com.qinshiji.registry.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * rpc调用处理
 * @author qinshiji
 * @data 2019/7/30 10:33
 */
public class InvokeHandler extends ChannelInboundHandlerAdapter {
    /**
     * 监听读取事件
     * @param ctx 通道信息
     * @param msg classInfo
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Invocation invocation = (Invocation) msg;
        String interfaceName = invocation.getInterfaceName();
        Class implClass = LocalRegister.get(interfaceName);
        String methodName = invocation.getMethodName();
        Method method = implClass.getMethod(methodName, invocation.getParamType());
        String result = (String) method.invoke(implClass.getDeclaredConstructor().newInstance(), invocation.getParams());
//        返回结果
        ctx.writeAndFlush(result);
    }

//    private String getImplClassName(ClassInfo msg) throws ClassNotFoundException {
//
//        System.out.println(msg);
//        //        接口路径
//        String interfacePath = "com.itcast.rpc.server";
//        String className = msg.getClassName();
//        int lastIndexOf = className.lastIndexOf(".");
////        接口名称
//        String interfaceName = className.substring(lastIndexOf);
//
//        Class aClass = Class.forName(interfacePath + interfaceName);
//        Reflections reflections = new Reflections(interfacePath);
////        获取接口下所有实现类
//        Set implClassSet = reflections.getSubTypesOf(aClass);
//
//        if (implClassSet==null||implClassSet.size()<1){
//            System.out.println("未找到实现类");
//            return null;
//        }
//         else if (implClassSet.size()>1){
//            System.out.println("找到多个实现类,不能确定使用哪一个");
//            return null;
//        } else {
////             返回实现类的类名
//            Object[] objects = implClassSet.toArray();
//            Class object = (Class) objects[0];
//           return object.getName();
//        }
//    }
}
