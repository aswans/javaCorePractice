package com.aswans.net.socket;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FileRecServer {
	public static void main(String[] args) throws Exception 
    {  
     //建立服务端Socket  
     ServerSocket ss = new ServerSocket(10005);  
     //接收客户端Socket  
     Socket fileLoaderSocket = ss.accept();  

     //打印连接信息  
     String ip = fileLoaderSocket.getInetAddress().getHostAddress();  
     System.out.println(ip + "...conncected");  

     //接收文件，并保存  
     InputStream in = fileLoaderSocket.getInputStream();  
     //实例化对象fileSave
     OutputStream fileSave = new FileOutputStream("D:\\野生动物.wmv");  
     //建立数组buf
     byte[] buf = new byte[1024];  
     int len = 0;  
     //判断是否读到文件末尾
      while((len=in.read(buf)) != -1) 
        {  
         fileSave.write(buf, 0, len);  
         //刷新
         fileSave.flush();  
        }  
     //返回文件复制情况信息  
     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fileLoaderSocket.getOutputStream()));  
     out.write("文件上传成功");  
     //刷新
     out.flush();  
     //资源关闭  
     ss.close();  
     fileLoaderSocket.close();  
     fileSave.close();  
   }  

}
