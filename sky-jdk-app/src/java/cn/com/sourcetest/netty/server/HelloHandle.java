package cn.com.sourcetest.netty.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.*;

/**
 * Created by sky.song on 2018/9/4.
 */
public class HelloHandle extends SimpleChannelHandler{

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
    }




    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
      //  System.out.println("messageReceived ");
        String message = (String) e.getMessage();
      //  System.out.println("received: "+ message);


//        Channel channel= ctx.getChannel();
//        ChannelBuffer buffer= ChannelBuffers.copiedBuffer(" i am fine ".getBytes());
//        channel.write(buffer);

    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed ");
       // super.channelClosed(ctx, e);
    }


    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected ");
    }

    @Override
    public void connectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("connectRequested ");
        //super.connectRequested(ctx, e);
    }

    @Override
    public void disconnectRequested(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("disconnectRequested  ");
        //super.disconnectRequested(ctx, e);
    }
}
