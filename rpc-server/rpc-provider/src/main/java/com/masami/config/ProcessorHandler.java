package com.masami.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */
public class ProcessorHandler implements Runnable{


    private Socket socket;

    private Map<String,Object> handleMap;


    public ProcessorHandler(Socket socket, Map<String, Object> handleMap) {
        this.socket = socket;
        this.handleMap = handleMap;
    }


    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
             objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object result = invoke(rpcRequest);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public Object invoke(RpcRequest rpcRequest ) throws Exception {

        Object[] args = rpcRequest.getArgs();

        Method method = null;

        if(args!=null){
            Class types[] = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i]  = args[i].getClass();
            }

            method = Class.forName(rpcRequest.getClassName()).getMethod(rpcRequest.getMethodName(),types);

        }else{
            method = Class.forName(rpcRequest.getClassName()).getMethod(rpcRequest.getMethodName());

        }

        return method.invoke(handleMap.get(rpcRequest.getClassName()),rpcRequest.getArgs());

    }
}
