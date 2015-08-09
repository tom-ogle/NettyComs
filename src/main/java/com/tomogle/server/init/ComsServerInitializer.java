package com.tomogle.server.init;

import com.tomogle.server.handlers.ExampleEchoServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ComsServerInitializer extends ChannelInitializer<SocketChannel> {

  @Override protected void initChannel(final SocketChannel channel) throws Exception {
    ChannelPipeline pipeline = channel.pipeline();
    // Add handlers here. See http://netty.io/5.0/api/io/netty/channel/ChannelPipeline.html
    // If handler is long running then specify a group to addLast so that the I/O thread isn't blocked
    pipeline.addLast("ExampleHandler", new ExampleEchoServerHandler());
  }
}
