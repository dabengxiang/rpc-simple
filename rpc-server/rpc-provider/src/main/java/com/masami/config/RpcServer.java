package com.masami.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: gyc
 * @Date: 2019/8/20 18:16
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    ExecutorService executorService= Executors.newCachedThreadPool();

    private int port ;


    private Map<String,Object>hashMap = new HashMap<String, Object>();

    public RpcServer(int port) {
        this.port = port;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> map = applicationContext.getBeansWithAnnotation(RpcService.class);
        if(map.size()!=0){
            for (String key : map.keySet()) {
                Object bean = map.get(key);
                RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
                Class<?> value = rpcService.value();
                hashMap.put(value.getName(),bean);
            }

        }

    }

    @Override
    public void afterPropertiesSet()  {
        ServerSocket serverSocket = null;
        try {
             serverSocket = new ServerSocket(this.port);
            while(true){//不断接受请求
                //每一个socket 交给一个processorHandler来处理
                Socket accept = serverSocket.accept();
                executorService.execute(new ProcessorHandler(accept,hashMap));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
