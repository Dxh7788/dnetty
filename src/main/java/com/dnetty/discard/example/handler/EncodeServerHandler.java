package com.dnetty.discard.example.handler;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dnetty
 *
 * @author xh.d
 * @since 2018/7/12 10:56
 */
public class EncodeServerHandler extends OneToOneEncoder {
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        String value = String.valueOf(msg);
        System.out.println("转码为:"+value);
        return value;
    }
}
