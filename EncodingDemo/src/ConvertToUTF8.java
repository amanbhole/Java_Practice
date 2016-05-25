import java.text.Normalizer;
import java.util.regex.Pattern;



public class ConvertToUTF8 {

	public static void main(String[] args) {
		String data="Nabisco Cheese Nips Avengersâ?¢ Cheddar Baked Snack Crackers 10 oz Box";
		String data2="Keeblerâ?¢ S'Mores Original Sandwich Cookies 7 oz. Wrapper";
		String data3="DoritosÂ® Nacho Cheese Tortilla Chips 11 oz. Bag";
		String outData = deAccent(data);
		
		System.out.println(outData);
		
	}


	public static String deAccent(String str) {
	    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD); 
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher(nfdNormalizedString).replaceAll("");
	}
	
}
