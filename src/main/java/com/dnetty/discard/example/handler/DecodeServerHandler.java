package com.dnetty.discard.example.handler;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;


/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dnetty
 *
 * @author xh.d
 * @since 2018/7/12 10:56
 */
public class DecodeServerHandler extends SimpleChannelUpstreamHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
        Object o = event.getMessage();
        if (o instanceof ChannelBuffer) {
            ctx.sendUpstream(event);
            return;
        }
        ChannelBuffer buf = (ChannelBuffer) event.getMessage();
        while (buf.readable()){
            System.out.println((char) buf.readByte());
            System.out.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        Channel channel = ctx.getChannel();
        channel.close();
    }
}
