package com.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author iPisces42
 * @version 1.0
 * @className Client
 * @description TODO
 * @date 2022年04月10日 1:12
 */
public class Client {
  public static void main(String[] args) {

    //创建两个线程,只需要一个线程组用于我们的实际处理(网络通信的读写)
    var workGroup = new NioEventLoopGroup();
//    通过辅助类去构造client,然后进行配置响应的配置参数

    var b = new Bootstrap();
    b.group(workGroup)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.SO_TIMEOUT, 3000)
        .option(ChannelOption.SO_RCVBUF, 1024 * 32)
        .option(ChannelOption.SO_SNDBUF, 1024 * 32)
        //    初始化ChannelInitializer
        .handler(
            new ChannelInitializer<>() {
              @Override
              protected void initChannel(Channel ch) throws Exception {}
            });
  }
}
