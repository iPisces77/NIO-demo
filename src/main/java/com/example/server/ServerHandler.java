package com.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.StandardCharsets;

/**
 * @author iPisces42
 * @version 1.0
 * @className ServerHandler
 * @description TODO
 * @date 2022年04月10日 0:59
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

  /**
   * 通道激活方法
   *
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.err.println("server channel active");
  }

  /**
   * 读写数据核心方法
   *
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //    读取客户端的数据(缓存中去取并打印到控制台)

    var buf = (ByteBuf) msg;
    var request = new byte[buf.readableBytes()];
    buf.readBytes(request);
    var requestBody = new String(request, StandardCharsets.UTF_8);
    System.err.println("Server" + requestBody);
    //    返回响应数据
    var responseBody = "响应数据" + requestBody;
    ctx.writeAndFlush(Unpooled.copiedBuffer(responseBody.getBytes()));
  }

  /**
   * 捕获异常方法
   *
   * @param ctx
   * @param cause
   * @throws Exception
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.fireExceptionCaught(cause);
  }
}
