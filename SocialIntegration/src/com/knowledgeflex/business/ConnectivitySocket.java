package com.knowledgeflex.business;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JPanel;

import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;

import com.google.gson.Gson;
import com.knowledgeflex.dto.FDomainDTO;
import com.knowledgeflex.dto.GDomainDTO;
import com.knowledgeflex.ui.KnowledgeFlex;

public class ConnectivitySocket {

	// Facebook

	/*Aman*/
		private static final String API_KEY = "";
		private static final String SECRET = "";

	/*Aman - TestApp*/
	//	private static final String API_KEY = "";
	//	private static final String SECRET = "";
	// UserName =   ; Password = 

	//Google Plus
	
	/*Aman*/
	private static final String CLIENT_ID = "";
	private static final String CLIENT_SECRET = "";
	
	//Constants
	private static final String firstRequest = "https://graph.facebook.com/oauth/authorize?"
			+ "client_id="
			+ API_KEY
			+ "&redirect_uri=https://www.facebook.com/connect/login_success.html"
			+ "&scope=user_about_me,email";

	private static final String firstGRequest = "https://accounts.google.com/o/oauth2/auth?"
			+ "response_type=code"
			+ "&client_id=" + CLIENT_ID
			+ "&redirect_uri=urn:ietf:wg:oauth:2.0:oob"
			+ "&scope=profile email"
			+ "&state=" + UUID.randomUUID().toString()
			+ "&include_granted_scopes=false";

	private String secondRequest = "";
	private String secondGRequest = "";
	private String f_access_token = "";
	private String g_access_token = "";
	private String thirdRequest="";
	private String thirdGRequest="";
	//	private Long fExpireTime = 0L;
	//	private Long gExpireTime = 0L;
	private String fUserName;
	private String gUserName;

	public ConnectivitySocket() {

	}

	public String faceBookLogin() {

		final JFrame authFrame = new JFrame();
		// Create the JWebBrowser and add the WebBrowserAdapter
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		final JWebBrowser webBrowser = new JWebBrowser();
		System.out.println(firstRequest);
		webBrowser.navigate(firstRequest);
		webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
			@Override
			public void locationChanged(WebBrowserNavigationEvent e) {
				super.locationChanged(e);

				// Check if you left the location and were redirected to the next 
				// location
				String newResource = e.getNewResourceLocation();
				System.out.println(newResource);
				if (newResource.contains("https://www.facebook.com/connect/login_success.html?code=")){
					// If it successfully redirects you, get the verification code
					// and go for a second request
					String[] splits = e.getNewResourceLocation().split("=");
					System.out.println("code : "+splits[1]);
					authFrame.dispose();

					secondRequest = "https://graph.facebook.com/oauth/access_token";
					getFAccessToken(secondRequest, splits[1]);

					thirdRequest = "https://graph.facebook.com/v2.0/me";
					fUserName = getUserProfile(thirdRequest,f_access_token);
					KnowledgeFlex.setUserName(fUserName);
				}
			}
		});
		webBrowserPanel.add(webBrowser,BorderLayout.CENTER);
		authFrame.add(webBrowserPanel);
		authFrame.setSize(625, 500);
		authFrame.setVisible(true);

		return fUserName;
	}

	private void getFAccessToken(String url, String verificationCode) {
		HashMap<String, String> paramters = new HashMap<String, String>();

		paramters.put("client_id", API_KEY);
		paramters.put("redirect_uri", "https://www.facebook.com/connect/login_success.html");
		paramters.put("client_secret", SECRET);
		paramters.put("code", verificationCode);

		RequestPacket requestPacket = new RequestPacket(url, "GET", paramters,"");
		MyHttpClient client = new MyHttpClient();
		String response  = client.executeGetRequest(requestPacket);
		String[] parseResponse = response.split("&");
		for (String param : parseResponse) {
			if(param.contains("access_token")){
				String[] param2 = param.split("=");
				f_access_token = param2[1];
				System.out.println(f_access_token);
			} else if (param.contains("expire")) {
				//				String[] param2 = param.split("=");
				//				fExpireTime = System.currentTimeMillis() + Long.valueOf(param2[1]);
			}
		}

	}

	private String getUserProfile(String url, String accessToken) {
		HashMap<String, String> paramters = new HashMap<String, String>();

		paramters.put("fields", "id,name");
		paramters.put("access_token", accessToken);

		RequestPacket requestPacket = new RequestPacket(url, "GET", paramters,"");
		MyHttpClient client = new MyHttpClient();
		String response  = client.executeGetRequest(requestPacket);
		Gson gson = new Gson();
		FDomainDTO domainDTO = gson.fromJson(response, FDomainDTO.class);
		System.out.println("Display Name : " + domainDTO.getName());
		return domainDTO.getName();
	}

	public String googleLogin() {

		final JFrame authFrame = new JFrame();
		// Create the JWebBrowser and add the WebBrowserAdapter
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		final JWebBrowser webBrowser = new JWebBrowser();
		System.out.println(firstGRequest);
		webBrowser.navigate(firstGRequest);
		webBrowser.addWebBrowserListener(new WebBrowserAdapter() {
			@Override
			public void locationChanged(WebBrowserNavigationEvent e) {
				super.locationChanged(e);
				// Check if you left the location and were redirected to the next 
				// location
				String newResource = e.getNewResourceLocation();
				System.out.println(newResource);
				if (newResource.contains("https://accounts.google.com/o/oauth2/approval?")){
					String htmlTitle = webBrowser.getPageTitle();
					String[] parse = htmlTitle.split("&");
					String code = null;
					for (String param : parse) {
						if(param.contains("code")) {
							String[] param2 = param.split("=");
							code = param2[1];
						}
					}
					authFrame.dispose();

					if(code!=null && code!=""){
						secondGRequest = "https://accounts.google.com/o/oauth2/token";
						getGAccessToken(secondGRequest, code);

						thirdGRequest = "https://www.googleapis.com/plus/v1/people/me";
						gUserName = getGUserProfile(thirdGRequest, g_access_token);
						KnowledgeFlex.setUserName(gUserName);
					}
				}
			}
		});
		webBrowserPanel.add(webBrowser,BorderLayout.CENTER);
		authFrame.add(webBrowserPanel);
		authFrame.setSize(625, 500);
		authFrame.setVisible(true);

		return gUserName;
	}

	private void getGAccessToken(String url, String verificationCode) {
		HashMap<String, String> paramters = new HashMap<String, String>();

		paramters.put("client_id", CLIENT_ID);
		paramters.put("redirect_uri", "urn:ietf:wg:oauth:2.0:oob");
		paramters.put("client_secret", CLIENT_SECRET);
		paramters.put("code", verificationCode);
		paramters.put("grant_type", "authorization_code");

		RequestPacket requestPacket = new RequestPacket(url, "GET", paramters,"");
		MyHttpClient client = new MyHttpClient();
		String response  = client.executePostRequest(requestPacket);
		Gson gson = new Gson();
		GDomainDTO domainDTO = gson.fromJson(response, GDomainDTO.class);
		g_access_token = domainDTO.getAccess_token();
	}

	private String getGUserProfile(String url, String accessToken) {
		HashMap<String, String> paramters = new HashMap<String, String>();

		paramters.put("access_token", accessToken);

		RequestPacket requestPacket = new RequestPacket(url, "GET", paramters,"");
		MyHttpClient client = new MyHttpClient();
		String response  = client.executeGetRequest(requestPacket);
		Gson gson = new Gson();
		GDomainDTO domainDTO = gson.fromJson(response, GDomainDTO.class);
		System.out.println("Display Name : " + domainDTO.getDisplayName());
		return domainDTO.getDisplayName();
	}

}
