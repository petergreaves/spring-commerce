package com.acme.web;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.acme.web.validator.EmailValidator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/testing-beans.xml")
public class CustomerTests {

	@Autowired
	EmailValidator ev;
	
	@Test
	public void validateGoodEmail(){
		
		String e = "peter.greaves@foo.com";
		assertTrue(ev.valid(e));
		
		
	}
	
	@Test
	public void validateBadEmail(){
		
		String e = "peter.greaves_foo.com";
		assertFalse(ev.valid(e));
		
		
	}
}
