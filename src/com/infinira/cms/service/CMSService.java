package com.infinira.cms.service;

import java.util.List;
import com.infinira.cms.model.Customer;
import com.infinira.cms.dao.CustomerDAO;

public class CMSService implements ICMSService{
    
    public int createCustomer(Customer customer){
        return CustomerDAO.createCustomer(customer);
    }

    public List<Customer> getAllCustomer(){
        return CustomerDAO.getAllCustomer();
    }
    
    public void deleteCustomer(int custId){
        CustomerDAO.deleteCustomer(custId);
    }
    
    public void updateCustomer(Customer customer){
        CustomerDAO.updateCustomer(customer);
    }
    
    public Customer getCustomerById(int custId){
        return CustomerDAO.getCustomerById(custId);
    }
}