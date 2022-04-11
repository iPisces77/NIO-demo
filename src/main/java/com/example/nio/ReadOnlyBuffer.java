package com.example.nio;

import java.nio.ByteBuffer;

/**
 * @author iPisces42
 * @version 1.0
 * @className ReadOnlyBuffer
 * @description TODO
 * @date 2022年04月11日 19:13
 */
public class ReadOnlyBuffer {
  public static void main(String[] args) {
    //
    var buffer = ByteBuffer.allocate(64);
    for (int i = 0; i < 64; i++) {
      //
      buffer.put((byte) i);
    }
    //    读取
    buffer.flip();
//    得到一个只读的buffer
    var readOnlyBuffer = buffer.asReadOnlyBuffer();
//    读取
    while (readOnlyBuffer.hasRemaining()) {
      System.out.println(readOnlyBuffer.get());
      readOnlyBuffer.put((byte) 1);
    }
  }
}
