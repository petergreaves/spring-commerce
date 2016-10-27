package com.acme.web.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acme.web.domain.Customer;
import com.acme.web.jdbc.CustomerJDBCTemplate;

public class MainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		CustomerJDBCTemplate customerJDBCTemplate = (CustomerJDBCTemplate) context.getBean("customerJDBCTemplate");
		
		
		System.out.println("------Records Creation--------");
		customerJDBCTemplate.create("Peter", "Greaves", "p.greaves@foo.com");
		customerJDBCTemplate.create("John", "Smith", "jsmith@bar.com");
		customerJDBCTemplate.create("Mike", "Jones", "mj@mail.com");

		System.out.println("------Listing Multiple Records--------");
		List<Customer> customers = customerJDBCTemplate.listCustomers();
		for (Customer record : customers) {
			System.out.print("ID : " + record.getID());
			System.out.print(", First : " + record.getFirstName());
			System.out.println(", Last : " + record.getLastName());
			System.out.println(", Email : " + record.getEmail());
		}

		System.out.println("----Updating Record with ID = 2 -----");
		customerJDBCTemplate.update(2, "Sid", "Spencer", "ssp@anymail.com");

		System.out.println("----Listing Record with ID = 2 -----");
		Customer customer = customerJDBCTemplate.getCustomer(2);
		System.out.print("ID : " + customer.getID());
		System.out.print(", First Name : " + customer.getFirstName());
		System.out.println(", Last Name : " + customer.getLastName());
		System.out.println(", Email : " + customer.getEmail());

		
	}
}
