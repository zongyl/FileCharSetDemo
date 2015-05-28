package com.longlong;

import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.File;
import java.nio.charset.Charset;

public class Cpdetector {

	public String getFileEncoding(String filePath){
		
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(UnicodeDetector.getInstance());
		
		Charset charset = null;
		File file = new File(filePath);
		try {
			charset = detector.detectCodepage(file.toURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String charsetName = "GBK";
		if(charset != null){
			if(charset.name().equals("US-ASCII")){
				charsetName = "ISO-8859-1";
			}else if(charset.name().startsWith("UTF")){
				charsetName = charset.name();
			}
		}
		return charsetName;
	}
}
