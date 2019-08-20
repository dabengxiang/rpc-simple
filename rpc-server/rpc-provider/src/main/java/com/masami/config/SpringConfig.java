package com.masami.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */

@Configuration
@ComponentScan(basePackages = "com.masami")
public class SpringConfig {

    @Bean(name="rpcServer")
    public RpcServer rpcServer(){
        return new RpcServer(8080);
    }
}
