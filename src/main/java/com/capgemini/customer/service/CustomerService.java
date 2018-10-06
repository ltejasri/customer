package com.capgemini.customer.service;



import java.util.List;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerNotFoundException;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	public Customer findAllCustomerById(int customerId) throws CustomerNotFoundException;
	public void deleteCustomer(int customerId) throws CustomerNotFoundException;
	public Customer authenticate(Customer customer)throws AuthenticationFailedException, CustomerNotFoundException;
	public List<Customer> getAllCustomers();

}