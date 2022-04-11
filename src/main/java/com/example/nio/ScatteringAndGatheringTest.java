package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.util.List;

/**
 * @author iPisces42
 * @version 1.0
 * @className ScatteringAndGatheringTest
 * @description Scattering将数据写入到buffer时,可以采用buffer数组,依次写入,(分散),Gathering
 *     将数据从buffer中读取时,可以采用buffer数组,依次读取,(聚集)
 * @date 2022年04月11日 20:21
 */
public class ScatteringAndGatheringTest {

  public static void main(String[] args) {
    //    使用ServerSocketChannel
    try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
      var inetSocketAddress = new InetSocketAddress(7000);
      //      绑定端口到socket,并启动
      serverSocketChannel.socket().bind(inetSocketAddress);
      //      创建buffer数组
      var byteBuffers = new ByteBuffer[2];
      byteBuffers[0] = ByteBuffer.allocate(5);
      byteBuffers[1] = ByteBuffer.allocate(3);
      //      等待客户端连接(telnet)
      var accept = serverSocketChannel.accept();
      //      循环的读取
      int messageLength = 8;
      while (true) {
        int byteRead = 0;
        while (byteRead < messageLength) {
          //       分散读取
          var read = accept.read(byteBuffers);
          byteRead += read;
          System.out.println("byteRead" + byteRead);
          //          使用流打印,看看这个buffer的position和limit
          List.of(byteBuffers).stream()
              .map(buffer -> "position:" + buffer.position() + " limit:" + buffer.limit())
              .forEach(System.out::println);
        }
        List.of(byteBuffers).forEach(ByteBuffer::flip);
        //        将数据读出显示到客户端
        long byteWrite = 0;
        while (byteWrite < messageLength) {
          var write = accept.write(byteBuffers);
          byteWrite += write;
        }
        List.of(byteBuffers).forEach(ByteBuffer::clear);
        System.out.println("byteWrite" + byteWrite);
        System.out.println("byteRead" + byteRead);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
