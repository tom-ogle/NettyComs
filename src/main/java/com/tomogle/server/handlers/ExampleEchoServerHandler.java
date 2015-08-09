package com.tomogle.server.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ExampleEchoServerHandler extends ChannelHandlerAdapter {

  // Override superclass methods to handle various lifecycle events
  // Here are examples for reading and catching Exceptions
  @Override
  public void channelRead(final ChannelHandlerContext ctx, final Object msg) {
    System.out.println("Reading channel...");
    System.out.println();
    ByteBuf incomingBuffer = (ByteBuf) msg;
    ByteBuf outgoingBuffer = ctx.alloc().buffer(incomingBuffer.capacity());
    try {
      while(incomingBuffer.isReadable()) {
        char c = (char)incomingBuffer.readByte();
        System.out.print(c);
        System.out.flush();
        outgoingBuffer.writeChar(c);
      }
      ctx.writeAndFlush(outgoingBuffer);
    } finally {
      incomingBuffer.release();
      System.out.println("Released incoming buffer");
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    cause.printStackTrace();
    ctx.close();
  }
}
