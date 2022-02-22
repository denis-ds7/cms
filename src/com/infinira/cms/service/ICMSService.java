package com.infinira.cms.service;

import java.util.List;
import com.infinira.cms.model.Customer;
import com.infinira.cms.dao.CustomerDAO;

public interface ICMSService{
    
    public int createCustomer(Customer customer);
    public List<Customer> getAllCustomer();
    public void deleteCustomer(int custId);
    public void updateCustomer(Customer customer);
    public Customer getCustomerById(int custId);
}