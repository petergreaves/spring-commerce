package com.acme.web.service;

import java.util.List;

import com.acme.web.domain.Customer;

public interface CustomerService {
	
	Customer findById(Integer id);

	List<Customer> findAll();

	void saveOrUpdate(Customer user);

	void delete(int id);

}
