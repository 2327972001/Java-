package com.java.myuser;

import java.io.*;
import java.net.Socket;

public class UserThread implements Runnable {
    private Socket socket;

    public UserThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            String info = bufferedReader.readLine();
            System.out.println("客户端："+socket.getInetAddress().getHostAddress()+"说："+info);
            printStream.println(info);
            printStream.flush();

            bufferedReader.close();
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
