package com.tomogle.server;

import com.tomogle.server.init.ComsServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

  private int port;

  public Server(final int port) {
    this.port = port;
  }

  public void run() throws Exception {
    EventLoopGroup incomingConnectionGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      bootstrapServer(incomingConnectionGroup, workerGroup);
    } finally {
      incomingConnectionGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }

  }

  private void bootstrapServer(final EventLoopGroup incomingConnectionGroup, final EventLoopGroup workerGroup) throws InterruptedException {
    ServerBootstrap bootstrap = new ServerBootstrap()
        .group(incomingConnectionGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ComsServerInitializer());
    setChannelOptions(bootstrap);

    // Bind on all NICs
    ChannelFuture future = bootstrap.bind(port).sync();
    // Wait to close
    future.channel().closeFuture().sync();
  }

  private void setChannelOptions(final ServerBootstrap bootstrap) {
    // Set options for incoming connections channel
    bootstrap.option(ChannelOption.SO_BACKLOG, 128);
    // Set options for child handlers
    bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
  }
}
