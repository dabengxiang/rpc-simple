package com.masami;


import java.lang.reflect.Proxy;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */
public class RpcProxyClient {

    public static  <T>T proxyClient(Class<T> interfacesClz,String ip,int port){
       return (T) Proxy.newProxyInstance(interfacesClz.getClassLoader(),new Class[]{interfacesClz},new RemoteInvocationHandler(ip,port));
    }
}
