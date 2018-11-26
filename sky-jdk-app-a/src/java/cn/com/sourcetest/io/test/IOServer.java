package cn.com.sourcetest.io.test;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sky.song on 2018/9/3.
 */
public class IOServer {


    public static void main(String[] args) throws Exception {
        ServerSocket  server= new ServerSocket(8000);
        System.out.println("Server已经启动");


        ExecutorService executor= Executors.newCachedThreadPool();

        while(true){
            final  Socket socket =server.accept();
            System.out.println("client： " + socket.getLocalAddress());

            executor.execute(new Runnable()  {
                @Override
                public void run(){
                    try {
                        handle(socket);
                    }catch (Exception e){

                    }
                }
            });

        }

    }



    public static void handle(Socket socket) throws Exception{
         InputStream  stream=  socket.getInputStream();
         byte[] bytes= new byte[1024];
         while(true){


             try {
                 int read = stream.read(bytes);
                 if (read > -1) {
                     System.out.println(new String(bytes, 0, read));
                     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                     bw.write(new String(bytes, 0, read) + "reply>>>");
                     bw.flush();
                 } else {
                     System.out.println("客户端关闭1");
                     break;
                 }
             }catch (Exception e){
                 socket.close();
                 System.out.println("客户端关闭2");
             }
         }
    }
}
