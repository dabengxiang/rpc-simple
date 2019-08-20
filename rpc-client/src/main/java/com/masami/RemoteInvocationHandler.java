package com.masami;

import com.masami.config.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */
public class RemoteInvocationHandler implements InvocationHandler {

    private String serverIp;

    private int serverPort;

    public RemoteInvocationHandler(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(proxy.getClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(serverIp, serverPort);
       return  rpcNetTransport.send(rpcRequest);

    }
}
