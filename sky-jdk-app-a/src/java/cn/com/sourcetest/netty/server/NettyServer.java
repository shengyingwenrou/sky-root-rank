package cn.com.sourcetest.netty.server;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * netty test
 * Created by sky.song on 2018/9/4.
 */
public class NettyServer {

    public static void main(String[] args) {

        //服务类
        ServerBootstrap bootstrap =new ServerBootstrap();

        ExecutorService boss= Executors.newCachedThreadPool();
        ExecutorService worker= Executors.newCachedThreadPool();

        //设置NioSocket工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));


        final HashedWheelTimer timer= new HashedWheelTimer();

        // 设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline= Channels.pipeline();
                pipeline.addLast("decoder", new StringDecoder());
                pipeline.addLast("encoder", new StringEncoder());
                pipeline.addLast("idle",new IdleStateHandler(timer,5,5,8));
                pipeline.addLast("helloHandle",new HelloHandle());
               // pipeline.addLast("idleHandle",new IdleHandle());
                return pipeline;
            }
        });

        bootstrap.bind(new InetSocketAddress(8000));

    }

}
