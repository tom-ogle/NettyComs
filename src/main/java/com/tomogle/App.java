package com.tomogle;

import com.tomogle.server.Server;

public class App {
  public static void main(final String[] args) throws Exception {
    int port = 8080;
    new Server(port).run();
  }
}
