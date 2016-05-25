package ucfacmapitutorial;

import java.util.Map;
import com.google.gson.Gson;

/**
 * Custom Exception to return a JSON encoded error message when dealing with the Facebook API.
 *
 * @author Shane Chism (schism at acm dot org)
 */
public class FacebookAPIException extends Exception {

	private String errType, errMessage;
	
	/**
	 * This deals with serialized components in Java. It does not directly relate to the purpose of this tutorial.
	 * For more information: http://java.dzone.com/articles/dont-ignore-serialversionuid.
	 */
	private static final long serialVersionUID = 5560904358324172134L;
	
	public FacebookAPIException( String response ){
		
		// Initialize Google's JSON encoder/decoder class (this is a utility, it's API independent)
		Gson gson = new Gson();
		
		try {
			
			// Attempt to parse the server response (JSON assumed) into our FBError class
			// -- Explanation:
			// An example error string: {"error":{"message":"Unknown path components: \/1106460174","type":"OAuthException"}}
			// "error" is the equivalent of a Java Map Object (a type of associative array where a string key is mapped to a string result.
			// As we see in the example, message would be the key for a string value of "Unknown path components..."
			// When we call the .get( KEY ) method, it is the same operation as calling myArray[<index>] on a standard numeric array.			
			FBError requestError = gson.fromJson( response, FBError.class );
			
			// Assign the appropriate map values to our exception class' variables
			errType = requestError.error.get( "type" );
			errMessage = requestError.error.get( "message" );
			
		}catch( Exception e ){
			// Unable to parse the returned JSON string into the FBError object
			errType		= 	"Unknown Error";
			errMessage	= 	"An unkown error has occured. Unable to process response from server: \n" + response;
		}
		
	}
	
	/**
	 * Shows the error message returned from the triggering malformed Facebook API request.
	 * Error displays in the command line.
	 */
	public void show(){
	    Main.printHeader( "[Facebook Graph API Error] " + this.errType );
		System.out.println( this.errMessage );
		System.exit( 1 );
	}
	
	/**
	 * Returns the error message returned from the triggering malformed Facebook API request.
	 *
	 * @return String containing the message returned
	 */
	public String getMessage(){
		return this.errMessage;
	}
	
	/**
	 * Returns the error type returned from the triggering malformed Facebook API request.
	 *
	 * @return String containing the error type returned
	 */
	public String getType(){
		return this.errType;
	}
	
	/**
	 * Container for the JSON parse of a server returned error. JSON elements in the array are parsed to this.
	 *
	 * @author Shane Chism (schism at acm dot org)
	 */
	private class FBError {
		public Map<String,String> error;
	}
	
}