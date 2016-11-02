package com.acme.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.acme.web.dao.CustomerDAO;
import com.acme.web.domain.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	@Qualifier("customerJDBCTemplate")
	private CustomerDAO customerJDBCTemplate;


	@Override
	public Customer findById(Integer id) {
		Customer cust = customerJDBCTemplate.getCustomer(id);

		return cust;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		List<Customer> customers = new ArrayList<Customer>();
		
		customers=customerJDBCTemplate.listCustomers();
		return customers;
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		// TODO Auto-generated method stub
		
		if (customer.isNewCustomer()){
			
			customerJDBCTemplate.create(customer.getFirstName(), customer.getLastName(), customer.getEmail());
		}else{
			customerJDBCTemplate.update(customer.getID(), customer.getFirstName(), customer.getLastName(), customer.getEmail());
			
		}

	}

	@Override
	public void delete(int id) {
		customerJDBCTemplate.delete(id);

	}

}
