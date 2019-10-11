package com.qinshiji.entity;

import java.io.Serializable;

/**
 * 传递接口信息
 * @author qinshiji
 * @date 2019/10/10 17:34
 */
public class Invocation implements Serializable {
    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型列表
     */
    private Class[] paramType;
    /**
     * 参数列表
     */
    private Object[] params;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamType() {
        return paramType;
    }

    public void setParamType(Class[] paramType) {
        this.paramType = paramType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Invocation(String interfaceName, String methodName, Class[] paramType, Object[] params) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.paramType = paramType;
        this.params = params;
    }
}
