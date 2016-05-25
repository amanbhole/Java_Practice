package com.javapapers.java.gcm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.BasicConfigurator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/HttpGCMPush")
public class HttpGCMPush extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// Put your Google API Server Key here
	private static final String GOOGLE_SERVER_KEY = "";
	public HttpGCMPush() {
		super();
	}
	
	String TAG = "HttpGCMPush";
	static final String MESSAGE_KEY = "message";
	String myRegistrationID;
	String regId;
//	String path = "D:\\workspace\\JavaPractice\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\GCM-App-Server" + "\\GCMRegId.txt";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

//		myRegistrationID = "APA91bFKl1mIUqaqQA-aP0xPZ0RnpfVxS_AKEG37uRpjEo9pzXBy_Uc_e1P-5qSklmBXQdf_RidfnSquTuw293Y5W3VFGXdkfS6HGLg8YzK4djbbjpLJoFsSBWCs55OkWq7CLalX7EsUpnREpVXfyT4-_FGiseMTsuE2iMelxIeyTusK4ECySNc";
//		regId = "APA91bFKl1mIUqaqQA-aP0xPZ0RnpfVxS_AKEG37uRpjEo9pzXBy_Uc_e1P-5qSklmBXQdf_RidfnSquTuw293Y5W3VFGXdkfS6HGLg8YzK4djbbjpLJoFsSBWCs55OkWq7CLalX7EsUpnREpVXfyT4-_FGiseMTsuE2iMelxIeyTusK4ECySNc";
		
//		BufferedReader br = new BufferedReader(new FileReader(path));
		
		/* RegID for GCM Demo - S2 device*/
		regId = "APA91bHyCjCDFfM3CXT7dcZRjU66lwEmMQuFS3vnSdevrc8COXJYVXWbeY_Tz0D4HgCUpdLXp4lN3fgt9_UUjLNselVp7bR325URUPK3JJrWkj6TPyENognYfOhDx053w9_uXB71rrwaqyBsOqJOqiPr0HBtMnNLmA";
		
		/* RegID for Birdzi Demo - S2 device*/
		regId = "APA91bGQSfA-hzeqPSZl2TMZQPaGnFwTZ0cRfnIXgsM_KIGc1MpiqiFJHJE_qy35cwYolkebf2iCDCJkrjzzHEhsjGfOFCvKj2Pb7TEq3Pk_UFGolHVc0cSpuYWPqW-mbtK1vZ3WNJ6wnatgARYK-wjb-hZdvfTtPQ";
//		br.close();
		
			System.out.println(TAG + "Creating HTTP Connection");
			BasicConfigurator.configure();
			// Creating HTTP client
			HttpClient httpClient = new DefaultHttpClient();
			// Creating HTTP Post
			HttpPost httpPost = new HttpPost(
					"https://android.googleapis.com/gcm/send");

			System.out.println(TAG + " HTTP Connection created");

			// Building post parameters
			 JSONObject pushMessage = null;
		        try {		
					
					JSONObject messageBody = new JSONObject();
					messageBody.put(MESSAGE_KEY, "Hello Brother"); /* request.getParameter("message") */
					
					JSONArray regIds = new JSONArray();
					//regIds.put(myRegistrationID);
					regIds.put(regId);
					
					pushMessage = new JSONObject();
					//pushMessage.put("delay_while_idle", false);
					pushMessage.put("data", messageBody);				
					pushMessage.put("registration_ids", regIds);
					
				} catch (JSONException e1) {
					e1.printStackTrace();
				}

			System.out.println("====Value====" + pushMessage);
			// Url Encoding the POST parameters
			try {
				httpPost.setHeader("Authorization", GOOGLE_SERVER_KEY);
				httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json"); /* For plain-text : "application/x-www-form-urlencoded/charset=UTF-8" */

				StringEntity se = new StringEntity(pushMessage.toString());
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
				httpPost.setEntity(se);
				System.out.println(TAG + " data send");
			} catch (UnsupportedEncodingException e) {
				// writing error to Log
				e.printStackTrace();
			}

			// Making HTTP Request
			try {
				HttpResponse response1 = httpClient.execute(httpPost);

				int responseCode = response1.getStatusLine().getStatusCode();
	            String responseMsg = response1.getStatusLine().getReasonPhrase().toString();
	            
	            System.out.println("Http Response Code:" + responseCode);
				// writing response to log
				System.out.println("Http Response:" + responseMsg);
			} catch (ClientProtocolException e) {
				// writing exception to log
				e.printStackTrace();
			} catch (IOException e) {
				// writing exception to log
				e.printStackTrace();

			}
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
}
