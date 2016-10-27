package com.acme.web.map;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.acme.web.domain.Customer;

public class CustomerMapper implements RowMapper<Customer>{
	
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setID(rs.getInt("cust_id"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setEmail(rs.getString("email"));
	    return customer;
	   }
	

}
