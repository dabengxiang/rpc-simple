package com.masami.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @Author: gyc
 * @Date: 2019/8/20 18:16
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(RpcService.class);
        System.out.println(map);

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
