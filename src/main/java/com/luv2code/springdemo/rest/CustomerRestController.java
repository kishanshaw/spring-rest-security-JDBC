package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	
	//autowire the customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
		
	}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id){
		
		if(customerService.getCustomer(id)==null){
			throw new CustomerNotFoundException("Customer with id= "+id+" not found");
		}
		/*
		else if(id<0 ||(id>= customerService.getCustomers().size())){
			throw new CustomerNotFoundException("Customer with id= "+id+" not found");
			
		}*/
		return customerService.getCustomer(id);
		
	}
	
	
	//add a customer using POST call, jackson takes the json passed and converts it to customer POJO. @RequestBody is used take the request body as input(the POJO) in this method
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer customer){
		customer.setId(0);//id = 0,indicates insert, else it goes for an update call
		customerService.saveCustomer(customer);
		return customer;

	}
	
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer customer){
		customerService.saveCustomer(customer);
		return customer;

	}
	
	@DeleteMapping("/customers/{id}")
	public String deleteCustomer(@PathVariable int id){
		if(customerService.getCustomer(id)==null){
			throw new CustomerNotFoundException("Customer with id= "+id+" not found");
		}
		customerService.deleteCustomer(id);
		return "Customer with id= "+id+"deleted";

	}
		
}


