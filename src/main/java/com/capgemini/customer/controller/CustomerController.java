package com.capgemini.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.AuthenticationFailedException;
import com.capgemini.customer.exception.CustomerNotFoundException;
import com.capgemini.customer.service.CustomerService;

@RestController
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService service;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity = 
				new ResponseEntity<Customer>(service.addCustomer(customer), HttpStatus.OK);
			return responseEntity;	
	}
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		try {
			Customer customerId = service.findAllCustomerById(customer.getCustomerId());
			
			return new ResponseEntity<Customer>(service.updateCustomer(customer), HttpStatus.OK);
		                                                                                                                                                                          
		}
		catch(CustomerNotFoundException exception) {
	}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> findAllCustomerById(@PathVariable int customerId) {
		try {
			Customer customer= service.findAllCustomerById(customerId);
			return new ResponseEntity<Customer>(service.findAllCustomerById(customerId), HttpStatus.OK);
		}
		catch(CustomerNotFoundException exception) {
	}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
}
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId){
		try {
			Customer customer = service.findAllCustomerById(customerId);
			if(customer!= null) {
				service.deleteCustomer(customerId);
				return new ResponseEntity<Customer>(HttpStatus.OK);
			}
		}
		catch(CustomerNotFoundException exception) {
		}
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("/login")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) throws AuthenticationFailedException, CustomerNotFoundException{
		
		Customer customer1 = service.authenticate(customer);
		if (customer1 != null)
			return new ResponseEntity<Customer>(service.authenticate(customer), HttpStatus.OK);

		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List> listAllCustomers(){
		List<Customer> list = service.getAllCustomers();
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

}

