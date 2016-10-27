package com.acme.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.acme.web.domain.Customer;

@Component
public class CustomerFormValidator implements Validator {
	
	@Autowired
	@Qualifier("emailValidator")
	EmailValidator emailValidator;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.customerForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.customerForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");

		if(!emailValidator.valid(customer.getEmail())){
			errors.rejectValue("email", "Pattern.customerForm.email");
		}

		
		
	}

}
