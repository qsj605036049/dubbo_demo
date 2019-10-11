package com.qinshiji.entity;

import java.io.Serializable;

/**
 * 自定义url
 * @author qinshiji
 * @date 2019/10/10 17:12
 */
public class URL implements Serializable {
    /**
     * 主机ip
     */
    private String hostname;
    /**
     * 端口号
     */
    private Integer port;

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
