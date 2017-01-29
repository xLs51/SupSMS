package com.supinfo.supsms.utils;

import com.supinfo.supsms.globals.RegisterConstraints;

public class PatternValidator
{
	public static boolean isPhoneNumberValid(String phoneNumber)
	{
		String pattern = "^((\\+|00)33\\s?|0)[679](\\s?\\d{2}){4}$";

		if(phoneNumber.matches(pattern))
			return true;
		
		return false;
	}
	
	public static boolean isCreditCardNumberValid(String creditCardNumber)
	{
		String pattern = "^(?:4[0-9]{12}(?:[0-9]{3})?" +           	// Visa
						"|  5[1-5][0-9]{14}" +                 		// MasterCard
						"|  3[47][0-9]{13}" +                  		// American Express
						"|  3(?:0[0-5]|[68][0-9])[0-9]{11}" +  		// Diners Club
						"|  6(?:011|5[0-9]{2})[0-9]{12}" +     		// Discover
						"|  (?:2131|1800|35\\d{3})\\d{11}" +     	// JCB
						")$";
		
		if(creditCardNumber.matches(pattern))
			return true;
		
		return false;
	}
	
	public static boolean isPasswordValid(String password)
	{
		if(password.length() >= RegisterConstraints.PASSWORD_LENGTH)
			return true;

		return false;
	}
}
