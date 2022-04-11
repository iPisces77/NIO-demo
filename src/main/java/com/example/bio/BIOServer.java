package com.example.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

/**
 * @author iPisces42
 * @version 1.0
 * @className BIOServer
 * @description TODO
 * @date 2022年04月11日 12:11
 */
public class BIOServer {
  public static void main(String[] args) throws IOException {
    var executorService = Executors.newCachedThreadPool();

    // 创建线程池
    //    如果有客户端连接,就创建一个线程,与之通信(单独写一个方法)
    var serverSocket = new ServerSocket(6666);
    System.out.println("服务器启动成功");
    while (true) {
      System.out.println("等待连接......");
      var accept = serverSocket.accept();
      System.out.println("连接到一个客户端");
      executorService.execute(
          new Runnable() {
            @Override
            public void run() {
              //          可以和客户端通信
              handler(accept);
            }
          });
    }
  }
  //  编写一个handler方法,和客户端通信
  public static void handler(Socket socket) {

    try (var inputStream = socket.getInputStream();
        var outputStream = socket.getOutputStream(); ) {
      System.out.println(
          "线程信息:" + Thread.currentThread().getId() + ":" + Thread.currentThread().getName());
      System.out.println("read......");
      var buffer = new byte[1024];
      var len = 0;
      while ((len = inputStream.read(buffer)) != -1) {
        System.out.println(new String(buffer, 0, len));
        //        outputStream.write(buffer, 0, len);
        //        outputStream.flush();

      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      System.out.println("关闭和client的连接");
    }
  }
}
