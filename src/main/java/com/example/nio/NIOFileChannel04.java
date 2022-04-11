package com.example.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author iPisces42
 * @version 1.0
 * @className NIOFileChannel04
 * @description TODO
 * @date 2022年04月11日 18:54
 */
public class NIOFileChannel04 {
  public static void main(String[] args) throws IOException {
    //
    var file = new File("D:\\file01.txt");
    var fileInputStream = new FileInputStream(file);
    var fileOutputStream = new FileOutputStream("D:\\file02.txt");

//    获取各个流对应的filechannel
    var inputStreamChannel = fileInputStream.getChannel();
    var outputStreamChannel = fileOutputStream.getChannel();
//    使用transferForm\
    outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

  }
}
