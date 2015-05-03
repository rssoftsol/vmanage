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
public class SmsCallGet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SmsCallGet test = new SmsCallGet();
		String text = "Test sms from Shekhar";
		String phoneNo = "8451046250";
		test.sendMessage(text, phoneNo);
	}
	public String sendMessage(String text, String phoneNo) {
		
		StringBuffer response = new StringBuffer();
		try {
			final TrustManager[] trustAllCerts = new TrustManager[] { 
					new X509TrustManager() 
					{
						@Override
						public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
						}
						@Override
						public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
						}
						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return null;
						}
					} };
			


			final SSLContext sslContext = SSLContext.getInstance( "SSL" );
			sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory( sslContext.getSocketFactory() );
			URL url = new URL(getURLPath(text, phoneNo));
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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}

	private String getURLPath(String text, String phoneNo) {
		String twar = getURL()+"?username="+username+"&password="+password+"&sender_id="+senderId+"&route="+route
				+ "&phonenumber="+phoneNo+"&message="+URLEncoder.encode(text);
		return twar;
	}

	@Value("${smsConfig.websiteLink}")
	private String websiteLink;
	
	@Value("${smsConfig.username}")
	private String username;
	
	@Value("${smsConfig.password}")
	private String password;
	
	@Value("${smsConfig.senderId}")
	private String senderId;
	
	@Value("${smsConfig.route}")
	private String route;
	
	
	private String getURL() {
		return websiteLink;
	}
	public String getWebsiteLink() {
		return websiteLink;
	}
	public void setWebsiteLink(String websiteLink) {
		this.websiteLink = websiteLink;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
}
