package com.dnetty.example.support;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Copyright (C) 2017-2018 https://www.htouhui.com - A project by dnetty
 *
 * @author xh.d
 * @since 2018/6/27 14:20
 */
public class SimpleStringAdapter extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        CharSequence charSequence = in.readCharSequence(in.readableBytes(), Charset.forName("utf-8"));
        out.add(new String(charSequence.toString()));
    }
}
