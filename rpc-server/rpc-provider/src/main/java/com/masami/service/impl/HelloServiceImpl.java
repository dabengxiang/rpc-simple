package com.masami.service.impl;

import com.masami.HelloService;
import com.masami.config.RpcService;

/**
 * @Author: gyc
 * @Date: 2019/8/20 18:06
 */
@RpcService(value = HelloService.class,version = "1.0")
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHi(String name) {
        System.out.println("向"+name+"sayHi");
    }
}
