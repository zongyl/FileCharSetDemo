package com.longlong;

import java.io.File;

public class Files {

	public static void main(String[] args) throws Exception{
		
//		new Files().listFiles(new File("E:\\zl\\XGPS\\src"));
//		
//		
		//E:\zl\XGPS\src\com\adapter\DevAdapter.java
		/*String filePath = "E:\\zl\\XGPS\\src\\com\\adapter\\DevAdapter.java";
		Cpdetector dete = new Cpdetector();
		FileCharsetConverter converter = new FileCharsetConverter();
		String charset = dete.getFileEncoding(filePath);
		String content = converter.getFileContent(new File(filePath), charset);
		System.out.println("charset:"+charset);
		System.out.println("content:"+content);*/
	//	converter.saveFile(new File(filePath), "UTF-8", content);

		//new FileCharsetConverter().convert(new File("E:\\zl\\XGPS\\AndroidManifest.xml"), "UTF-8");
		new FileCharsetConverter().convert(new File("E:\\NetDisk\\Project\\Android\\GPS\\testwifi\\src\\com\\example"), "UTF-8");
		System.exit(0);
	}
	
	
	
	public void listFiles(File file){
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f : files){
				listFiles(f);
			}
		}else{
			System.out.println("file:"+file.getAbsolutePath());
			System.out.println("encoding:"+new Jchardet().guessFileEncoding(file));
			System.out.println("encoding:"+new Cpdetector().getFileEncoding(file.getAbsolutePath()));
		}
		
	}
}
