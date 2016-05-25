package com.knowledgeflex.business;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class MyHttpClient {

	HttpClient client = null;
	private HttpResponse httpResponse;
	private HttpEntity httpEntity;

	public MyHttpClient() {
		client = HttpClientBuilder.create().build();
	}

	public String executeGetRequest(RequestPacket packet) {
		String response = null;
		try {
			System.out.println("URL : " + packet.url);

			URIBuilder builder = new URIBuilder();
			builder.setPath(packet.url);
			if (packet.paramters != null && packet.paramters.size() > 0) {
				Set<Entry<String, String>> entries = packet.paramters.entrySet();
				for (Entry<String, String> entry : entries) {
					builder.setParameter(entry.getKey(), entry.getValue());
					System.out.println("key::" + entry.getKey() + " value:: " + entry.getValue());
				}
			}
			URI uri = builder.build();
			HttpGet httpGET = new HttpGet(uri);

			httpResponse = client.execute(httpGET);
			httpEntity = httpResponse.getEntity();

			System.out.println("Response : " +httpResponse.getStatusLine()+ "\nResponse code : "+httpResponse.getStatusLine().getStatusCode());

			if (httpEntity != null) {
				response = EntityUtils.toString(httpEntity,"UTF-8");
//				System.out.println("IN_REST : "+ response);
			}
		} catch(ClientProtocolException e) {
			System.out.println("ClientProtocolException : "+e.getMessage());
		} catch(IOException e) {
			System.out.println("IOException : "+e.getMessage());
		} catch(Exception e) {
			System.out.println("Exception : "+e.getMessage());
		}

		return response;
	}

	public String executePostRequest(RequestPacket packet) {
		String response = null;
		try {
			System.out.println("URL : " + packet.url);
			HttpPost httpPOST = new HttpPost(packet.url);

			List<NameValuePair> paramList = new ArrayList<NameValuePair>();
			
			if (packet.paramters != null && packet.paramters.size() > 0) {
				Set<Entry<String, String>> entries = packet.paramters.entrySet();
				for (Entry<String, String> entry : entries) {
					paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					System.out.println("key::" + entry.getKey() + " value:: " + entry.getValue());
				}
			}
			
			httpPOST.setEntity(new UrlEncodedFormEntity(paramList));
			httpResponse = client.execute(httpPOST);
			httpEntity = httpResponse.getEntity();
			
			System.out.println("Response : " +httpResponse.getStatusLine()+ "\nResponse code : "+httpResponse.getStatusLine().getStatusCode());
			
			if (httpEntity != null) {
				response = EntityUtils.toString(httpEntity,"UTF-8");
//				System.out.println("IN_REST : "+ response);
			}
		} catch(ClientProtocolException e) {
			System.out.println("ClientProtocolException : "+e.getMessage());
		} catch(IOException e) {
			System.out.println("IOException : "+e.getMessage());
		} catch(Exception e) {
			System.out.println("Exception : "+e.getMessage());
		}
		return response;
	}
	
	public String executePostRequest2(RequestPacket packet) {
		String response = null;
		try {
			System.out.println("URL : " + packet.url);
			URIBuilder builder = new URIBuilder();
			builder.setPath(packet.url);
			
			if (packet.paramters != null && packet.paramters.size() > 0) {
				Set<Entry<String, String>> entries = packet.paramters.entrySet();
				for (Entry<String, String> entry : entries) {
					builder.setParameter(entry.getKey(), entry.getValue());
					System.out.println("key::" + entry.getKey() + " value:: " + entry.getValue());
				}
			}
			
			URI uri = builder.build();
			HttpPost httpPOST = new HttpPost(uri);
			httpResponse = client.execute(httpPOST);
			httpEntity = httpResponse.getEntity();
			
			System.out.println("Response : " +httpResponse.getStatusLine()+ "\nResponse code : "+httpResponse.getStatusLine().getStatusCode());
			
			if (httpEntity != null) {
				response = EntityUtils.toString(httpEntity,"UTF-8");
//				System.out.println("IN_REST : "+ response);
			}
		} catch(ClientProtocolException e) {
			System.out.println("ClientProtocolException : "+e.getMessage());
		} catch(IOException e) {
			System.out.println("IOException : "+e.getMessage());
		} catch(Exception e) {
			System.out.println("Exception : "+e.getMessage());
		}
		return response;
	}
}
