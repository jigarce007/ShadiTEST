package com.example.shaaditest.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;


public class WebserviceReader {

	public static StringBuilder builder;


	public static String WebserviceCallServicerGet(String strURL){
		String response = "";
		String URL = null;

			URL = URLConstants.BASE_URL +strURL;


		CoreConstants.PageCode = "0";
		Logger.e("2", "==================== Get API Call ==========================");
		Logger.e("WebserviceReader URL", "URL - "+URL);
		try{
			
			if(URL.startsWith("http://")) {
			HttpURLConnection client = null;
			java.net.URL urlToRequest = new URL(URL);
			client = (HttpURLConnection)
	            urlToRequest.openConnection();
			client.setRequestMethod("GET");
			client.setRequestProperty("Content-Type", "application/json");

				try{
					CoreConstants.PageCode = ""+client.getResponseCode();
					Logger.e("Page Response Code", "Code - "+client.getResponseCode());
				}catch(Exception e){
					//e.printStackTrace();
					CoreConstants.PageCode = ""+client.getResponseCode();
					Logger.e("Page Response Code", "Code - "+client.getResponseCode());
				}

            Scanner inStream = new Scanner(client.getInputStream());
            while(inStream.hasNextLine())
            	response+=(inStream.nextLine());

			}
            
			if(URL.startsWith("https://")){
				
				/*SSLContext sslcontext = SSLContext.getInstance("TLSv1");

	            sslcontext.init(null,
	                    null,
	                    null);
	            SSLSocketFactory NoSSLv3Factory = new NoSSLv3SocketFactory(sslcontext.getSocketFactory());
				
				HttpsURLConnection.setDefaultSSLSocketFactory(NoSSLv3Factory);*/

				HttpsURLConnection client = null;
				java.net.URL urlToRequest = new URL(URL);
				client = (HttpsURLConnection)
		            urlToRequest.openConnection();
				client.setRequestMethod("GET");
				client.setRequestProperty("Content-Type", "application/json");

				try{
					CoreConstants.PageCode = ""+client.getResponseCode();
					Logger.e("Page Response Code", "Code - "+client.getResponseCode());
				}catch(Exception e){
					//e.printStackTrace();
					CoreConstants.PageCode = ""+client.getResponseCode();
					Logger.e("Page Response Code", "Code - "+client.getResponseCode());
				}
	            Scanner inStream = new Scanner(client.getInputStream());
	            while(inStream.hasNextLine())
	            	response+=(inStream.nextLine());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return response;
		
	}


}
