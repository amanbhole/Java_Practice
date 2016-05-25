package com.util.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	public static void main(String[] argv) {

//		String sPhoneNumber = "605-8889999";
//		String sPhoneNumber = "605-88899991";
//		String sPhoneNumber = "605-888999A";
		String sPhoneNumber = "(605) 888-9999";

		Pattern pattern = Pattern.compile("((\\d{3}))\\s\\d{3}-\\d{4}");
		System.out.println(pattern.pattern());
		Matcher matcher = pattern.matcher(sPhoneNumber);

		if (matcher.matches()) {
			System.out.println("Phone Number Valid");
		}
		else
		{
			System.out.println("Phone Number must be in the form XXX-XXXXXXX");
		}
		
		String result = sPhoneNumber.replaceAll("(?[0-9]{3})?[-. ]?[0-9]{3}[-. ]?[0-9]{4}", "");
		System.out.println(result);
	}

}
