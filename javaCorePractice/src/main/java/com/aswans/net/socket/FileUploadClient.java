package com.aswans.net.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileUploadClient {
	public static void main(String[] args) throws UnknownHostException,
			IOException {
		// 建立Socket服务
		Socket fileLoaderSocket = new Socket("192.168.20.199", 10005);
		// 从客户端本地读取文件,并写入socket的输出流中
		OutputStream out = fileLoaderSocket.getOutputStream();
		// 实例化对象fileReader
		File file = new File(
				"D:\\1.txt");
		InputStream fileRead = new FileInputStream(file);
		// 建立数组
		byte[] buf = new byte[1024];
		int len = 0;
		// 判断是否读到文件末尾
		while ((len = fileRead.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		// 告诉服务端，文件已传输完毕
		fileLoaderSocket.shutdownOutput();
		// 获取从服务端反馈的信息
		BufferedReader in = new BufferedReader(new InputStreamReader(
				fileLoaderSocket.getInputStream()));
		String serverBack = in.readLine();
		System.out.println(serverBack);
		// 资源关闭
		fileLoaderSocket.close();
		fileRead.close();
	}
}