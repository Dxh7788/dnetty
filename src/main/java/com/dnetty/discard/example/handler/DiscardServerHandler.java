package com.dnetty.discard.example.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dnetty
 *
 * @author xh.d
 * @since 2018/7/12 10:56
 */
public class DiscardServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Object o = e.getMessage();
        if (o instanceof ChannelBuffer){
            ctx.sendUpstream(e);
            return;
        }
        System.out.println("1---");
        //discard
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        Channel channel = ctx.getChannel();
        channel.close();
    }
}
