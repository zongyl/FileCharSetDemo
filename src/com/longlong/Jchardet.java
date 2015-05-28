package com.longlong;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

public class Jchardet {

	private boolean found = false;
	private String encoding = null;
	
	public static void main(String[] args){
		System.out.println("........"+new Jchardet().guessFileEncoding(
				//new File("E:\\zl\\XGPS\\src\\com\\sharesdk\\wxapi\\WXEntryActivity.java")));
		new File("E:\\zl\\XGPS\\src\\cn\\sharesdk\\onekeyshare\\CustomerLogo.java")));
	}
	
	public String guessFileEncoding(File file) {
		String ret = "";
		try {
			ret = guessFileEncoding(file, new nsDetector());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public String guessFileEncoding(File file, nsDetector det) throws Exception{
		det.Init(new nsICharsetDetectionObserver() {
			@Override
			public void Notify(String charset) {
				encoding = charset;
				found = true;
			}
		});
		
		BufferedInputStream imp = new BufferedInputStream(new FileInputStream(file));
		byte[] buf = new byte[1024];
		int len;
		boolean done = false;
		boolean isAscii = false;

		while((len = imp.read(buf, 0, buf.length)) != -1){
			isAscii = det.isAscii(buf, len);
			if(isAscii){
				break;
			}
			done = det.DoIt(buf, len, false);
			if(done){
				break;
			}
		}
		imp.close();
		det.DataEnd();
		
		if(isAscii){
			encoding = "ASCII";
			found = true;
		}
		
		if(!found){
			String[] prob = det.getProbableCharsets();
			for(String str : prob){
				encoding += ","+str;
			}
		}
		return encoding;
	}
	
}
