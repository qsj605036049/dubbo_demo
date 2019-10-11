package com.qinshiji.protocol;

import com.qinshiji.entity.Invocation;

/**
 * @author qinshiji
 * @date 2019/10/11 14:49
 */
public interface ProtocolClient {
    public String send(String hostname,Integer port, Invocation invocation);
}
