package com.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author iPisces42
 * @version 1.0
 * @className Server
 * @description TODO
 * @date 2022年04月10日 0:34
 */
public class Server {
  public static void main(String[] args) throws InterruptedException {
    // 创建两个线程池,一个用于网络连接, 一个用于我们的实际处理
    var bossGroup = new NioEventLoopGroup();
    var workGroup = new NioEventLoopGroup();
    //    通过辅助类去构造server/Client
    var b = new ServerBootstrap();
    //    绑定两个线程组
    b.group(bossGroup, workGroup)
        //    配置NioServerSocketChannel
        .channel(NioServerSocketChannel.class)
        //        设置链接超时时间
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
        //        设置 TCP BACKLOG 参数=sync队列+accept队列
        .option(ChannelOption.SO_BACKLOG, 1024)
        //        设置配置项 通信不延迟
        .childOption(ChannelOption.TCP_NODELAY, true)
        //        设置配置项 接收与发送缓冲区大小
        .childOption(ChannelOption.SO_RCVBUF, 1024 * 32)
        .childOption(ChannelOption.SO_SNDBUF, 1024 * 32)
        //        进行初始化 ChannelInitializer,用于构建双向链表pipeline,添加业务handler处理
        .childHandler(
            new ChannelInitializer<SocketChannel>() {
              @Override
              protected void initChannel(SocketChannel ch) throws Exception {
                // 这里仅添加一个业务处理器,ServerHandler
                ch.pipeline().addLast(new ServerHandler());
              }
            });
    //    服务器绑定端口,并启动服务,使用channel级别的监听close端口,阻塞的方式
    var channelFuture = b.bind(8765).sync();
    channelFuture.channel().closeFuture().sync();

    //    释放资源
    bossGroup.shutdownGracefully();
    workGroup.shutdownGracefully();
  }
}
