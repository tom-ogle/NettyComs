package com.tomogle.client;

import com.tomogle.client.init.ComsClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

  private String host;
  private int port;

  public Client(final String host, final int port) {
    this.host = host;
    this.port = port;
  }

  public void connect() throws Exception {
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      bootstrapClient(workerGroup);
    } finally {
      workerGroup.shutdownGracefully();
    }
  }

  private void bootstrapClient(final EventLoopGroup  workerGroup) throws InterruptedException {
    Bootstrap clientBootstrap = new Bootstrap();
    clientBootstrap
        .group(workerGroup)
        .channel(NioSocketChannel.class)
        .handler(new ComsClientInitializer());
    setChannelOptions(clientBootstrap);

    // Connect
    ChannelFuture future = clientBootstrap.connect(host, port).sync();
    // Wait to close
    future.channel().closeFuture().sync();

  }

  private void setChannelOptions(final Bootstrap clientBootstrap) {
    clientBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
  }
}
