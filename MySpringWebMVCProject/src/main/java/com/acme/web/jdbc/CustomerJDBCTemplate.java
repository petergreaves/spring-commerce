package com.acme.web.jdbc;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;


import com.acme.web.dao.CustomerDAO;
import com.acme.web.domain.Customer;
import com.acme.web.map.CustomerMapper;

public class CustomerJDBCTemplate implements CustomerDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		jdbcTemplateObject = new JdbcTemplate(dataSource);

	}

	@Override
	public void create(String firstName, String lastName, String email) {
				
		String SQL = "insert into Customer (first_name, last_name, email) values (?, ?, ?)";
	      
	      jdbcTemplateObject.update( SQL, firstName, lastName, email);
	      System.out.println("Created Record, first Name = " + firstName + " last name = " + lastName + ", email : "+email);
	      return;

	}

	@Override
	public Customer getCustomer(Integer id) {
		String SQL = "select * from customer where cust_id = ?";
		Customer c=jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new CustomerMapper());
		return c;
	}

	@Override
	public List<Customer> listCustomers() {
		
		List<Customer> list = null;
		String SQL = "select * from customer";
		list=jdbcTemplateObject.query(SQL, new CustomerMapper());
		return list;
	}

	@Override
	public void delete(Integer id) {
		
		String SQL = "delete from customer where cust_id = ?";
		jdbcTemplateObject.update(SQL, id);
		return;	

	}

	@Override
	public void update(Integer id, String firstName, String lastName, String email) {
		String SQL = "update customer set first_name = ?, last_name = ?, email= ?  where cust_id = ?";
	    jdbcTemplateObject.update(SQL, new Object[]{firstName, lastName, email, id});
	    System.out.println("Updated Record with ID = " + id );

	}

}
