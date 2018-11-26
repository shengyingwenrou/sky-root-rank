package cn.com.sourcetest.io.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**ssds
 * Created by sky.song on 2018/9/3.
 */
public class NIOServer {


    private Selector  selector;

    public static void main(String[] args) throws Exception {
        NIOServer server = new NIOServer();
        server.initNIOServer1(8888);
        server.listen1();
    }



    public void initNIOServer1(int port) throws Exception{
        ServerSocketChannel serverChannel=ServerSocketChannel.open();
        serverChannel.configureBlocking(false);


        serverChannel.bind(new InetSocketAddress(port));
        this.selector=Selector.open();
        serverChannel.register(selector,SelectionKey.OP_ACCEPT);
    }


    public void listen1() throws Exception{
        while(true){
            selector.select();
            Iterator<?> ite=this.selector.selectedKeys().iterator();
            while(ite.hasNext()){
                SelectionKey key= (SelectionKey)ite.next();
                ite.remove();
                handle1(key);
            }

        }
    }


    public void handle1 (SelectionKey key) throws Exception {

       /* SocketChannel channel=(SocketChannel)key.channel();
        ByteBuffer buffer  = ByteBuffer.wrap(" client start handle".getBytes());
        channel.write(buffer);*/

        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);

        ByteBuffer write = ByteBuffer.wrap(" i am  ready to handle ".getBytes());
        channel.write(write);


        if (key.isAcceptable()) {
            handleAccepet1(key);
        } else if (key.isReadable()) {
            handleRead1(key);
        }
    }

    public void handleAccepet1(SelectionKey key) throws Exception{
        ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();

        SocketChannel channel = serverChannel.accept();

        channel.configureBlocking(false);
        ByteBuffer buffer= ByteBuffer.wrap(" we are start accepe chatting ".getBytes());
        channel.write(buffer);

        channel.register(this.selector,SelectionKey.OP_READ);
    }

    public void handleRead1(SelectionKey key) throws Exception{
        SocketChannel channel=(SocketChannel)key.channel();
        ByteBuffer buffer= ByteBuffer.allocate(1024);

        try {
            int read = channel.read(buffer);

                if (read > -1) {
                    byte[] byteArr = buffer.array();
                    System.out.println(" client say :" + new String(byteArr).trim());

                    ByteBuffer writer= ByteBuffer.wrap("yes".getBytes());
                    channel.write(writer);
                } else {
                    System.out.println(" client break out ");
                    key.cancel();
                }
        }catch (Exception e){
            System.out.println(" client break out exception  ");
            key.cancel();
        }
    }

































    private void initNIOServer(int port) throws Exception{
        System.out.println("server 服务已经启动");
        ServerSocketChannel serverSocketChannel= ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        this.selector=Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }


    private void listen() throws Exception{
        while(true){
            selector.select();
            Iterator<?> interator = this.selector.selectedKeys().iterator();
            while(interator.hasNext()){
                SelectionKey index= (SelectionKey)interator.next();
                System.out.println("SelectionKey："+index);
                interator.remove();
                handle(index);
            }
        }
    }


    private void handle(SelectionKey key) throws Exception{
        if (key.isAcceptable()) {
            handleAcceptable(key);
        } else if (key.isReadable()) {
            handleReadable(key);
        }
    }

    private void handleAcceptable(SelectionKey key) throws Exception{
        ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
        // 在这里可以给客户端发送信息哦
        SocketChannel channel= serverChannel.accept();
        ByteBuffer writeBuffer = ByteBuffer.wrap("we are start chatting ".getBytes());
        channel.write(writeBuffer);
        channel.configureBlocking(false);
        System.out.println("新的客户端连接");
        channel.register(this.selector,SelectionKey.OP_READ);
    }


    private void handleReadable(SelectionKey key) throws Exception{
        SocketChannel channel=(SocketChannel)key.channel();
        // 创建服务的缓冲区
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        int read = channel.read(buffer);
        if (read > -1) {
            byte[] data=buffer.array();
            System.out.println("received data：" + new String(data).trim());
            ByteBuffer writeBuffer = ByteBuffer.wrap("yes ".getBytes());
            channel.write(writeBuffer);
        }else{
            System.out.println("客户端已经关闭");
            key.cancel();
        }
    }


}
