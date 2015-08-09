package com.tomogle.client.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ExampleClientHandler extends ChannelHandlerAdapter {

  @Override
  public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
    ByteBuf buffer = (ByteBuf) msg;
    try {
      // TODO: Main client read logic goes here, e.g. when reading from server
      // 
    } finally {
      buffer.release();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
