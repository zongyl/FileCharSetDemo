package com.longlong;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

public class FileCharsetConverter {

	/**
	 * 转换文件编码格式
	 * @param file 
	 * @param toCharset
	 */
	public void convert(File file, String toCharset){
		try {
			convert(file, null, toCharset, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 转换文件编码格式
	 * @param file
	 * @param fromCharset
	 * @param toCharset
	 * @param filter
	 * @throws Exception
	 */
	public void convert(File file, String fromCharset, String toCharset, FileFilter filter) throws Exception{
		
		if(file.isDirectory()){
			File[] fileList = null;
			if(filter == null){
				fileList = file.listFiles();
			}else{
				fileList = file.listFiles(filter);
			}
			for(File f : fileList){
				convert(f, fromCharset, toCharset, filter);
			}
		}else{
			/**
			 * if (filter == null
					|| filter.accept(file.getParentFile(), file.getName()))
			 */

			if(fromCharset == null){
				fromCharset = new Cpdetector().getFileEncoding(file.getAbsolutePath());
			}

			System.out.println(file.getAbsolutePath()+"\n charset:"+fromCharset);
			
			String content = getFileContent(file, fromCharset);
			saveFile(file, toCharset, content);
		}
		
	}
	
	/**
	 * 读取文件
	 * @param file
	 * @param charset 文件编码
	 * @return
	 * @throws Exception
	 */
	public String getFileContent(File file, String charset) throws Exception{
		if(!Charset.isSupported(charset)){
			throw new UnsupportedCharsetException(charset);
		}
		InputStream inputStream = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(inputStream, charset);
		char[] chs = new char[(int)file.length()];
		reader.read(chs);
		String str = new String(chs).trim();
		reader.close();
		return str;
	}
	
	/**
	 * 保存文件
	 * @param file
	 * @param charset
	 * @param content
	 * @throws Exception
	 */
	public void saveFile(File file, String charset, String content) throws Exception{
		if(!Charset.isSupported(charset)){
			throw new UnsupportedCharsetException(charset);
		}
		OutputStream outputStream = new FileOutputStream(file);
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
		writer.write(content);
		writer.close();
	}
}
