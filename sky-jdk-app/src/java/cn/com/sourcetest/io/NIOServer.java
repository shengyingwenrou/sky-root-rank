package cn.com.sourcetest.io;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * desc: cn.com.SourceTest.io
 * User: sky.song@magicwifi.com.cn
 * Date: 2018/8/9
 * Time: 14:56
 */
public class NIOServer {

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
        NIOServer server = new NIOServer();
        server.initNioServer(8000);
        server.listen();

    }


    public  void initNioServer(int port) throws Exception {
        // 获得一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        // 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }



    public void listen() throws Exception{
        System.out.println("服务端启动成功！");
        while(true){
            selector.select();
            Iterator<?> ite = this.selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey kry= (SelectionKey)ite.next();
                ite.remove();
                handle(kry);
            }
        }
    }

    public void handle(SelectionKey key) throws Exception{

        if(key.isAcceptable()){
            handleAccept(key);
        }else if(key.isReadable()){
            handleRead(key);
        }
    }

    // 处理链接请求
    public void handleAccept(SelectionKey key) throws Exception{
        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        // 获得和客户端连接的通道
        SocketChannel channel = server.accept();
        // 设置成非阻塞
        channel.configureBlocking(false);
        // 在这里可以给客户端发送信息哦
        System.out.println("新的客户端连接");
        // 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
        channel.register(this.selector,SelectionKey.OP_READ);
    }

    public void  handleRead(SelectionKey key) throws Exception{
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel)key.channel();
        // 创建服务的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        if(read>0){
            byte[] data =buffer.array();
            String msg = new String(data).trim();
            System.out.println("服务器收到消息:"+msg);
            // 回写数据
            ByteBuffer  outerBuffer = ByteBuffer.wrap("yes dear ".getBytes());
            channel.write(outerBuffer);
        }else{
            System.out.println("客户端关闭");
            key.cancel();
        }
    }

}
