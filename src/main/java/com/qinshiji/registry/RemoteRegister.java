package com.qinshiji.registry;

import com.qinshiji.entity.URL;

import java.io.*;
import java.util.*;

/**
 * 远程注册中心
 * @author qinshiji
 * @date 2019/10/10 17:11
 */
public class RemoteRegister {
    private static Map<String, List<URL>> REGISTER = new HashMap<String, List<URL>>();

    /**
     * 注册
     * @param interfaceName 接口名
     * @param url 主机地址
     */
    public static void register(String interfaceName,URL url){
        List<URL> urlList = REGISTER.get(interfaceName);
        if (urlList==null){
            urlList = new ArrayList<URL>();
        }
        urlList.add(url);
        REGISTER.put(interfaceName, urlList);
        saveFile();
    }

    /**
     * 获取主机地址信息
     * @param interfaceName 接口名
     * @return 主机地址信息
     */
    public static List<URL> get(String interfaceName){
        REGISTER = getFile();
        return REGISTER.get(interfaceName);
    }

    /**
     * 持久化储存
     */
    private static void saveFile(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("item.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("item.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
