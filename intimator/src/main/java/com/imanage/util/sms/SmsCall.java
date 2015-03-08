package com.imanage.util.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsCall {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SmsCall test = new SmsCall();
		String text = "Test sms from Shekhar for checking sms gateway.";
		String senderId = "SMSIND";
		String phoneNo = "8451046250";
		test.sendMessage(senderId, phoneNo, text);
	}
	private void sendMessage(String senderId, String phoneNo, String text) {
		
		try {
			URL url = new URL(getURL());
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(getXMLString(senderId, phoneNo, text));
			wr.flush();
	        wr.close();
	        BufferedReader bufRead = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        StringBuffer strBuff = new StringBuffer();
	        String str = new String();
            while((str = bufRead.readLine())!=null){
            	strBuff.append(str);
            }
            bufRead.close();
            System.out.println(strBuff);
	         
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("deprecation")
	private String getXMLString(String senderId, String phoneNo, String text) {
		String twar = "<pushsms>" +
			"<username>chandrashekhar.m.patil@gmail.com</username>" +
			"<password>shekhar123</password>" +
			"<senderid>"+senderId+"</senderid> " +
			"<messages><message pno='"+phoneNo+"' msg='"+text+"'></message>" +
			//"<messages><message pno='1234567890' msg='Test sms from 1234567890. Thanks for choosing our service - XXXXX'></message>" +
			"</messages></pushsms>";
		twar = URLEncoder.encode(twar);
		twar = "xmlstring="+twar;
		return twar;
	}

	private String getURL() {
		
		return "http://smsc.biz/xmlapi/send";
		
	}
}



