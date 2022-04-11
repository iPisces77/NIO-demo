package com.example.nio;

import java.nio.ByteBuffer;

/**
 * @author iPisces42
 * @version 1.0
 * @className NIOByteBufferPutGet
 * @description TODO
 * @date 2022年04月11日 19:07
 */
public class NIOByteBufferPutGet {
  public static void main(String[] args) {
    //
    var buffer = ByteBuffer.allocate(64);
//    类型化方式放入数据
    buffer.putInt(100);
    buffer.putLong(9);
    buffer.putChar('尚');
    buffer.putFloat(1.1f);
    //    取出
    buffer.flip();
    System.out.println(

    );

    System.out.println(buffer.getLong());
    System.out.println(buffer.getLong());
    System.out.println(buffer.getChar());
    System.out.println(buffer.getFloat());
  }
}
