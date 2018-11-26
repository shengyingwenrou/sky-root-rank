package cn.com.sourcetest.netty.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * Created by sky.song on 2018/9/4.
 */
public class HiHandle extends SimpleChannelHandler{

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived ");
        String message = (String) e.getMessage();
        System.out.println("received: "+ message);

        Channel channel= ctx.getChannel();
        ChannelBuffer buffer= ChannelBuffers.copiedBuffer(" i am fine ".getBytes());
        channel.write(buffer);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {

        System.out.println("exceptionCaught ");
        super.exceptionCaught(ctx, e);
    }

    /**
     * 必须是链接已经建立，关闭通道的时候才会触发
     */
    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    /**
     * channel关闭的时候触发
     */
    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
