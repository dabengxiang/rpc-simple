package com.masami;

import com.masami.config.RpcRequest;

import java.io.*;
import java.net.Socket;

/**
 * Date:2019/8/20
 * Author:gyc
 * Desc:
 */
public class RpcNetTransport {

    private String serverIp;

    private int serverPort;

    public RpcNetTransport(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public Object send(RpcRequest rpcRequest){
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            Socket socket = new Socket(serverIp, serverPort);
             objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
             objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();
            return result;

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
        return null;
    }
}
