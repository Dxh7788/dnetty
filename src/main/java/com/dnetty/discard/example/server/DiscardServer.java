package com.dnetty.discard.example.server;

import com.dnetty.discard.example.handler.EncodeServerHandler;
import com.dnetty.discard.example.handler.DiscardServerHandler;
import com.dnetty.discard.example.handler.DecodeServerHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dnetty
 *
 * @author xh.d
 * @since 2018/7/12 11:01
 */
public class DiscardServer {

    //不使用线程池的server
    public static void main(String[] args) {
        ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),Executors.newCachedThreadPool());
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.setFactory(factory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                DiscardServerHandler handler=new DiscardServerHandler();
                EncodeServerHandler encoder=new EncodeServerHandler();
                DecodeServerHandler decoder=new DecodeServerHandler();
                ChannelPipeline pipeline = Channels.pipeline(decoder, encoder, handler);
                return pipeline;
            }
        });
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.bind(new InetSocketAddress(8080));
    }
}
