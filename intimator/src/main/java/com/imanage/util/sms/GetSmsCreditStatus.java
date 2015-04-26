package com.imanage.util.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GetSmsCreditStatus {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetSmsCreditStatus test = new GetSmsCreditStatus();
		test.getSmsCreditStatus();
	}
	public String getSmsCreditStatus() {
		
		StringBuffer response = new StringBuffer();
		try {
			
			URL url = new URL(getURLPath());
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			//System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//put logger here
			System.out.println("Response from sms gateway: "+response.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	private String getURLPath() {
		String twar = getURL()+"?username="+getUsername()+"&password="+getPassword();
		return twar;
	}

	@Value("${smsConfig.creditStatusLink}")
	private String creditStatusLink;
	
	@Value("${smsConfig.username}")
	private String username;
	
	@Value("${smsConfig.password}")
	private String password;
	
	private String getURL() {
		//return http://smsc.biz/api/getcredits?username=USERNAME&password=PASSWORD
		return creditStatusLink;
	}
	
	public String getUsername() {
		//return chandrashekhar.m.patil@gmail.com
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		//return shekhar123
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
