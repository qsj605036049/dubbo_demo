package com.qinshiji.http;

import com.alibaba.fastjson.JSON;
import com.qinshiji.entity.Invocation;
import com.qinshiji.entity.User;
import com.qinshiji.registry.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * http请求处理
 *
 * @author qinshiji
 * @date 2019/10/10 17:03
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Invocation invocation = JSON.parseObject(req.getInputStream(), Invocation.class);
            String interfaceName = invocation.getInterfaceName();
            Class implClass = LocalRegister.get(interfaceName);
            String methodName = invocation.getMethodName();
            Method method = implClass.getMethod(methodName, invocation.getParamType());
            String result = (String) method.invoke(implClass.getDeclaredConstructor().newInstance(), invocation.getParams());
            IOUtils.write(JSON.toJSONString(result), resp.getOutputStream(),"utf-8");
        } catch (IOException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
