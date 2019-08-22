package com.masami;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */
public class App {

    public static void main(String[] args) {
        HelloService helloService = RpcProxyClient.proxyClient(HelloService.class, "127.0.0.1", 8080);
        String girlName = helloService.getGirlName();
        System.out.println(girlName);
    }
}
