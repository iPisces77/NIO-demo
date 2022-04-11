package com.example.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author iPisces42
 * @version 1.0
 * @className NIOFileChannel02
 * @description TODO
 * @date 2022年04月11日 18:22
 */
public class NIOFileChannel02 {
  public static void main(String[] args) {
    //
    var file = new File("d:\\file01.txt");
    try (var fileInputStream = new FileInputStream(file)) {
      var channel = fileInputStream.getChannel();
      //    创建缓冲区
      var byteBuffer = ByteBuffer.allocate((int) file.length());
      //    将通道的数据读入到Buffer
      channel.read(byteBuffer);
      System.out.println(new String(byteBuffer.array()));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
