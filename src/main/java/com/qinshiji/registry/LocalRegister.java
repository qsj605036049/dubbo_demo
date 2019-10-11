package com.qinshiji.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地注册
 * @author qinshiji
 * @date 2019/10/10 17:05
 */
public class LocalRegister {
    private static Map<String,Class> map  = new HashMap<String, Class>();

    /**
     * 注册
     * @param interfaceName 接口名
     * @param implClass 接口实现类
     */
    public static void register(String interfaceName,Class implClass){
        map.put(interfaceName, implClass);
    }

    /**
     * 获得接口实现类
     * @param interfaceName 接口名
     * @return 接口实现类
     */
    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
