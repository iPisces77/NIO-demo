package com.example.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author iPisces42
 * @version 1.0
 * @className NIOFileChannel03
 * @description TODO
 * @date 2022年04月11日 18:33
 */
public class NIOFileChannel03 {
  public static void main(String[] args) {
    //
    try (var fileInputStream = new FileInputStream("D:\\file01.txt")) {
      var inputChannel = fileInputStream.getChannel();
      var fileOutputStream = new FileOutputStream("2.txt");
      var outputChannel = fileOutputStream.getChannel();
      var buffer = ByteBuffer.allocate(1024);

      while (true){
        buffer.clear();
        var read = inputChannel.read(buffer);
        if(read == -1){
//        读取结束
          break;
        }
        buffer.flip();
        outputChannel.write(buffer);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
