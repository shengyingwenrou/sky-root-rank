package cn.com.sourcetest.netty.server;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sky.song on 2018/9/5.
 */
public class IdleHandle extends SimpleChannelHandler{
    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        super.handleUpstream(ctx, e);
        SimpleDateFormat format = new SimpleDateFormat("ss");

        if(e instanceof IdleStateEvent){
            // IdleStateEvent event=(IdleStateEvent)e;
            //System.out.println(" event state is："+event.getState());
            if(((IdleStateEvent)e).getState()== IdleState.ALL_IDLE){

                System.out.println(" kill online ");
                ChannelFuture write= ctx.getChannel().write("you lost online , will close ");
                write.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        ctx.getChannel().close();
                    }
                });

            }

        }else{
            super.handleUpstream(ctx,e);
        }
        System.out.println(" now is :"+format.format(new Date()));

    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);

        System.out.println(" message is :"+e.getMessage());
    }


    //    @Override
//    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
//        super.channelIdle(ctx, e);
//        SimpleDateFormat format = new SimpleDateFormat("ss");
//        System.out.println(" now is :"+format.format(new Date()));
//    }
}
