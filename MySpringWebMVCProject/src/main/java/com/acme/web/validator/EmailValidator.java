package com.acme.web.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailValidator  {

	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public boolean valid(String email) {
		// TODO Auto-generated method stub
		// find a regex for an RFC 822 email
		
	//	return email.
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN) ;
		
	    Matcher matcher = pattern.matcher(email);
		return matcher.matches();


	}

}
