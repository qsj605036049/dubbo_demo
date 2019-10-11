package com.qinshiji.utils;

import com.qinshiji.entity.URL;

import java.util.List;
import java.util.Random;

/**
 * @author qinshiji
 * @date 2019/10/10 18:12
 */
public class RandomUrl {
    public static URL getUrl(List<URL> urlList){
        Random random = new Random();
        int i = random.nextInt(urlList.size());
        return urlList.get(i);
    }
}
