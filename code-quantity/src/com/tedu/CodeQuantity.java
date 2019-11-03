package com.tedu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class CodeQuantity {
	static int results = 0;  //定义接收代码量的参数
	
	public static void main(String[] args) {
		textFile();
	}

	//代码总量
	public static void textFile() {
		File file = new File("D:\\test-workspach\\jt"); //目标文件路径
		lists(file);
		System.out.println("代码总量："+results+" 行");
	}


	//获取代码总量
	private static void lists(File file) {
		File[] lists = file.listFiles();  //获取当前目录下的所有文件夹和文件的File对象
		for (File f : lists) {  //遍历File对象
			String name = f.getName();   //获取File的name
			if (name.equals("pom.xml")) {  //如果是pom.xml，则获取它 的代码量
				results += result(f);
			}else {
				if (name.substring(0, 2).equals("jt")) {  //如果是jt-目录，则递归
					lists(f);
				}else if(name.equals("src")) {  //如果是src目录，另写一个方法，递归所有文件和文件夹
					src(f);
				}else {
					if (name.equals("pom.xml")) { //如果是pom.xml，则获取它 的代码量
						results += result(f);
					}
				}
			}
		}
	}

	//递归src目录下的所有文件和文件夹
	private static void src(File f) {
		File[] lists = f.listFiles();  //获取当前目录下的所有文件夹和文件的File对象
		for (File file : lists) {  //遍历File对象
			String s = file.getName();  //获取File的name
			if (file.isFile()) {  //如果当前file是文件，则获取它的代码量
				if (s.matches("^.+\\.(java|yml|properties)$")) {  //如果该文件符合后缀
					results += result(file);
				}
			}else { //否则递归遍历
				src(file);
			}
		}
	}

	//获取文件的代码量，并返回
	private static int result(File f) {
		int length = 0;
		try { 
			//换行流、编码转换流、文件流
			BufferedReader in =new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
			String line;
			//接收一行字符串,末尾有换行符
			while ((line = in.readLine()) != null ) {
				line = line.replaceAll("\\s+$",""); //去除该代码的右边的空白字符
				if (line.length()>0) {//如果这一行的长度大于0，代码量+1
					System.out.println(line);
					length++;
				}
			}
			System.out.println(f);   //当前代码全路径
			System.out.println("你一共写了\" "+length+" \"行代码！你真厉害  ~_~ -_- ~_~");
		} catch (Exception e) {
		}
		return length;		
	}

}
