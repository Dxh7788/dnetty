package com.dnetty.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dnetty
 *
 * @author xh.d
 * @since 2018/6/26 13:35
 */
public class TimerServer {

    private int port;

    public TimerServer(int port) {
        this.port = port;
    }

    /*
    * NioEventLoopGroup 是一个多线程的事件循环处理 I/O操作,Netty提供多种EventLoopGroup(事件循环组)来实现不同类型的传输.
    * ServerBootstrap 一个辅助类用来创建Server,也可以使用Channel直接创建,但是这是一个巨大的过程,而且一般不需要这么做.
    * NioServerSocketChannel创建一个实例来接收客户端请求
    * */
    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimerServerHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>> Netty Timer 服务端开启....");
        new TimerServer(8080).run();
    }
}
