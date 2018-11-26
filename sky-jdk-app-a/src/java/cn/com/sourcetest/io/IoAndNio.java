package cn.com.sourcetest.io;

import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.net.SocketServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * desc: cn.com.SourceTest.io
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/9
 * Time: 14:56
 */
public class IoAndNio {

    private Selector selector;

    /**
     *   面向Stream    阻塞IO    无(io)
     *   面向Buffer    非阻塞IO  Selectors
     */
    public static void main(String[] args) throws Exception {
        //ioTest();
        //nioTest();
        //testOio();
        //NIOServer server
        ExecutorService executor = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8888);
        System.out.println("server 已经启动 ");
        while(true){
            final Socket socket = server.accept();
            System.out.println("来了一个客户端:"+socket.getInetAddress());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    handle(socket);
                }
            });
        }
    }



    public static void  testOio() throws Exception{

    }


    public static void handle(Socket socket) {
        byte[] bytes = new byte[1024];
        try {
            InputStream stream = socket.getInputStream();
            while (true) {
                int read = stream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            try {
                socket.close();
                System.out.println("InputStream stream:" + e.getMessage());
            } catch (Exception e1) {
                System.out.println("socket.close:" + e1.getMessage());
            }
        }
    }


    public static void ioTest()  throws IOException {

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            File f = new File("G:/迅雷下载/otp_src_17.0.tar.gz");
            fis = new FileInputStream(f);

            File of = new File("G:/迅雷下载/otp_src_17.0.io.test.tar.gz");
            fos = new FileOutputStream(of);

            byte[] bytes = new byte[1024 * 1024];
            while (fis.read(bytes) > 0) {
                fos.write(bytes);
            }
        } catch (Exception e) {

        } finally {

            if (null != fis) {
                fis.close();
            }

            if (null != fos) {
                fos.close();
            }

        }
    }


    public static void nioTest() throws IOException{

    }


}
