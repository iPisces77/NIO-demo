package com.example.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author iPisces42
 * @version 1.0
 * @className NioFileChannel01
 * @description TODO
 * @date 2022年04月11日 18:05
 */
public class NioFileChannel01 {
  public static void main(String[] args) {
    //

    String str = "hello world";
    //    创建一个输出流->channel
    try (var fileOutputStream = new FileOutputStream("D:\\file01.txt")) {
      //    这个类型实际上是,FileChannelImpl
      var channel = fileOutputStream.getChannel();
      //    创建一个缓冲区ByteBuffer
      var byteBuffer = ByteBuffer.allocate(1024);
//    将str放入byteBuffer中
      byteBuffer.put(str.getBytes());
      byteBuffer.flip();
//    将byteBuffer中的数据写入channel中
      channel.write(byteBuffer);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
