package ucfacmapitutorial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

/**
 * Connect to the Facebook API and download data.
 *
 * @author Shane Chism (schism at acm dot org)
 */
public class FacebookAPI {
	
	/**
	 * Query the Facebook API and load public user data.
	 *
	 * @param UID Facebook User ID (or username) of the user to pull data from.
	 *
	 * @throws FacebookAPIException on Facebook Graph API error
	 * @throws Exception on connection failure or JSON parse failure
	 * 
	 * @return {@link FacebookAPI.FBObject_PublicUser} object containing public user data
	 */
	public FBObject_PublicUser get( String UID ) {
		
		// Initialize our return value
		String json = "";
		FBObject_PublicUser result = null;
		
		try {
			
			// Open a connection to the URL at our URI API request string
			URLConnection fbAPI = new URL( "https://graph.facebook.com/" + UID ).openConnection();
			// Declare an HTTPURLConnection resource, used to get the response code (and in the error case, evaluate)
			HttpURLConnection fbConnection = ( ( HttpURLConnection ) fbAPI );
			
			// Determine if the request was a success (Code 200)
			if( fbConnection.getResponseCode() != 200 ){
				// Request was not successful, pull error message from the server
				BufferedReader reader = new BufferedReader( new InputStreamReader( fbConnection.getErrorStream() ) );
				
				String input;
				while( ( input = reader.readLine() ) != null )
					json += input;
				
				reader.close();
				// Generate a custom exception that parses the JSON output and displays to the user
				throw new FacebookAPIException( json );
			}
			
			// Get the response text from the server
			BufferedReader reader = new BufferedReader( new InputStreamReader( fbAPI.getInputStream() ) );
			
			String input;
			while( ( input = reader.readLine() ) != null )
				json += input;
			
			reader.close();
			
			// Convert the JSON request to our class object
			Gson gson = new Gson();
			return gson.fromJson( json, FBObject_PublicUser.class );
			
		}catch( FacebookAPIException e ){
			e.show();
		}catch( Exception e ){
			System.out.println( "Facebook API Fatal Error: " + e.getMessage() );
		}
		
		return result;
		
	}
	
	/**
	 * Public User Object storing the result of a generic Facebook API request:
	 * <pre>https://graph.facebook.com/[USER_ID]</pre>
	 * This is a Facebook API Object (demonstrating JSON conversion and object properties).
	 * 
	 * @see <a href="http://goo.gl/f2QYh" target="_blank">Facebook API Documentation (attributes explained)</a>
	 * @see <a href="http://goo.gl/NnGxt" target="_blank">Sample Facebook API Request</a>
	 */
	public class FBObject_PublicUser implements FB_PublicUser {
		
		private String id;
		private String name;
		private String first_name;
		private String last_name;
		private String link;
		private String username;
		private String gender;
		private String locale;
		private String type;
		
		public String getId(){ return id; }
		public String getName(){ return name; }
		public String getFirstName(){ return first_name; }
		public String getLastName(){ return last_name; }
		public String getLink(){ return link; }
		public String getUsername(){ return username; }
		public String getGender(){ return gender; }
		public String getLocale(){ return locale; }
		public String getType(){ return type; }
		
	}
	
}

/**
 * Interface for {@link FacebookAPI.FBObject_PublicUser} (Facebook Graph API). Allows access to requested user data.
 * 
 * @see <a href="http://goo.gl/f2QYh" target="_blank">Facebook API Documentation (attributes explained)</a>
 */
interface FB_PublicUser {

	/** The user's Facebook ID. */
	public String getId();
	/** The user's full name. */
	public String getName();
	/** The user's first name. */
	public String getFirstName();
	/** The user's last name. */
	public String getLastName();
	/** The URL of the profile for the user on Facebook. */
	public String getLink();
	/** The user's Facebook username. */
	public String getUsername();
	/** The user's gender. Possible values: {female|male}. */
	public String getGender();
	/** The user's locale. String containing the ISO Language Code and ISO Country Code. */
	public String getLocale();
	/** Account type. For a user, value "user." */
	public String getType();

}
