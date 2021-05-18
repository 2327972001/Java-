package com.java.myuser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mytcp {
    private ServerSocket serverSocket;
    private Socket socket;
    private int port;
    public void startup(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try{
            port = 2327;//填写服务器端口
            serverSocket = new ServerSocket(port);
            //等待客户端连接
            System.out.println("等待用户连接中...");
            while(true){
                socket = serverSocket.accept();
                System.out.println("客户端："+socket.getInetAddress().getHostAddress()+" 已连接！");
                executorService.execute(new UserThread(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Mytcp my = new Mytcp();
        my.startup();
    }
}
