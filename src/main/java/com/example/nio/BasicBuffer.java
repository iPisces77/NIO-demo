package com.example.nio;

import java.nio.IntBuffer;

/**
 * @author iPisces42
 * @version 1.0
 * @className BasicBuffer
 * @description TODO
 * @date 2022年04月11日 13:50
 */
public class BasicBuffer {
  public static void main(String[] args) {
    // 创建一个buffer,大小为5,即可以存放5个int
    var buffer = IntBuffer.allocate(5);
    //    向buffer,存放数据
    buffer.put(10);
    buffer.put(11);
    buffer.put(12);
    buffer.put(13);
    buffer.put(14);
    //    如何从buffer读取数据
    //    将buffer转换,读写切换
    buffer.flip();

    while (buffer.hasRemaining()) {
      System.out.println(buffer.get());
    }
  }
}
