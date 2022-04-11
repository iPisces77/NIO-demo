package com.example.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel.MapMode;

/**
 * @author iPisces42
 * @version 1.0
 * @className MappedByteBufferTest
 * @description MappedByteBufferTest 可让文件直接在内存(堆外内存)修改,操作系统不需要拷贝一次
 * @date 2022年04月11日 20:09
 */
public class MappedByteBufferTest {
  public static void main(String[] args) {
    //
    try (var rw = new RandomAccessFile("D:\\file01.txt", "rw")) {
      var channel = rw.getChannel();
      /*
       参数1:FileChannel.MapMode.Read_Write使用的读写模式
       参数2:0,表示直接修改的起始位置
       参数3:5映射到内存的大小
       可以直接修改的范围就是0-5
      */
      var map = channel.map(MapMode.READ_WRITE, 0, 10);
      map.put(0, (byte) 'a');
      map.put(1, (byte) 'b');
      map.put(10, (byte) 'c');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
