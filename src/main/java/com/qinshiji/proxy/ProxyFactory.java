package com.qinshiji.proxy;

import com.alibaba.fastjson.JSON;
import com.qinshiji.constant.Constant;
import com.qinshiji.entity.Invocation;
import com.qinshiji.entity.URL;
import com.qinshiji.http.HttpClient;
import com.qinshiji.protocol.ProtocolClient;
import com.qinshiji.protocol.ProtocolClientFactory;
import com.qinshiji.registry.RemoteRegister;
import com.qinshiji.utils.RandomUrl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 代理工厂
 * @author qinshiji
 * @date 2019/10/10 18:00
 */
public class ProxyFactory {

    public static <T> T getProxyClass(Class interfaceClass){
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ProtocolClient client = ProtocolClientFactory.getClient(Constant.PROTOCOL);
                Invocation invocation = new Invocation(interfaceClass.getName(),method.getName() ,method.getParameterTypes(),args );
                List<URL> urls = RemoteRegister.get(interfaceClass.getName());
                URL url = RandomUrl.getUrl(urls);
                String result =  client.send(url.getHostname(), url.getPort(), invocation);
                return result;
            }
        });
    }
}
