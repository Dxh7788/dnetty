package com.dnetty.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dnetty
 *
 * @author xh.d
 * @since 2018/6/26 13:38
 */
public class CodecServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //version 1.0 Discard
        //System.out.println(msg);
        //((ByteBuf) msg).release();
        //Version 1.1 read by char
        /*ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }*/
        //version 1.2
        /*ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println(in.toString(CharsetUtil.UTF_8));
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }*/
        /*version 1.3向客户端回显msg*/
        String in = (String) msg;
        System.out.println(in);
//        ctx.write(msg);
//        ctx.flush();
        //ctx.write and ctx.flush = ctx.writeAndFlush
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
