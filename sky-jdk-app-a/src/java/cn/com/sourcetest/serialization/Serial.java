package cn.com.sourcetest.serialization;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by sky.song on 2018/9/12.
 */
public class Serial {


    public static void main(String[] args) throws  Exception {

       // ioStreamTest();

       // byteBufferTest();

        channelBufferTest();

    }


    public static  void  channelBufferTest(){
        ChannelBuffer buffer= ChannelBuffers.dynamicBuffer();
        buffer.writeInt(101);
        buffer.writeDouble(80.1);
        byte[] bytes=new byte[buffer.writerIndex()];
        buffer.readBytes(bytes);
        System.out.println(Arrays.toString(bytes));
        ChannelBuffer buffer2= ChannelBuffers.wrappedBuffer(bytes);
        System.out.println("int：" +buffer2.readInt());
        System.out.println("double：" +buffer2.readDouble());
    }


    /**
     * 缺点: ByteBuffer 不会自己扩容
     */
    public static void byteBufferTest(){
        int id = 11;
        int age = 101;
        ByteBuffer buffer= ByteBuffer.allocate(8);
        buffer.putInt(id);
        buffer.putInt(age);
        byte[] bytes=buffer.array();
        System.out.println(Arrays.toString(bytes));
        /////////////////////////////////
        ByteBuffer buffer2= ByteBuffer.wrap(bytes);
        System.out.println("id："+buffer2.getInt());
        System.out.println("age："+buffer2.getInt());
    }


    /**
     * 缺点: 转字节数组
     * @throws Exception
     */
    public static void ioStreamTest() throws  Exception {
        int id = 11;
        int age = 101;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(intToBytes(id));
        outputStream.write(intToBytes(age));
        byte[] arr =outputStream.toByteArray();
        System.out.println(Arrays.toString(arr));
        System.out.println(bytesToInt(arr));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(arr);
        byte[] idbytes=new byte[4];
        inputStream.read(idbytes);
        System.out.println("id："+bytesToInt(idbytes));

        byte[] agebytes=new byte[4];
        inputStream.read(agebytes);
        System.out.println("age："+bytesToInt(agebytes));
    }


    /**
     * 大小端字节序列
     * @param a
     * @return
     */
    public static byte[] intToBytes(int a) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (a >> 3 * 8);
        bytes[1] = (byte) (a >> 2 * 8);
        bytes[2] = (byte) (a >> 1 * 8);
        bytes[3] = (byte) (a >> 0 * 8);
        return bytes;
    }

    public static int bytesToInt(byte[] bytes) {
        return (bytes[0] << 3 * 8) |
                (bytes[1] << 2 * 8) |
                (bytes[2] << 1 * 8) |
                (bytes[3] << 0 * 8);
    }
}
