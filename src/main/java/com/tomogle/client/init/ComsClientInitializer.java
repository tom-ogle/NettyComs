package com.tomogle.client.init;

import com.tomogle.client.handlers.ExampleClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ComsClientInitializer extends ChannelInitializer<SocketChannel> {
  @Override protected void initChannel(final SocketChannel channel) throws Exception {
    ChannelPipeline pipeline = channel.pipeline();
    pipeline.addLast(new ExampleClientHandler());
  }
}
