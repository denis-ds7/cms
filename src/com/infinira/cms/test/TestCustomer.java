package com.infinira.cms.test;

import java.util.List;
import com.infinira.cms.service.CMSService;
import com.infinira.cms.service.ICMSService;
import com.infinira.cms.util.DBResource;
import com.infinira.cms.model.Customer;

public class TestCustomer{

    public static void main(String args[]){
    	
    	System.out.println(DBResource.self().getConnection());
    	
         
//        Customer customer = new Customer("First Name", "Last Name");
//        
//        ICMSService cmsService = new CMSService();
//        
//        System.out.println("Creating new Customer...");
//        int custId = cmsService.createCustomer(customer);
//        System.out.println(custId);
//        
//        System.out.println("Get All Customers...");
//        List<Customer> customersList = cmsService.getAllCustomer();
//        for(Customer customerData:customersList){
//            System.out.println(customerData.getCustId());
//            System.out.println(customerData.getFirstName());
//            System.out.println(customerData.getLastName());
//        }
//        
//        System.out.println("Deleting Customer...");
//        cmsService.deleteCustomer(25);
//         
//        System.out.println("Updating Customer...");
//        customer.setCustId(26);
//        customer.setFirstName("Chris");
//        customer.setLastName("Ronaldo");
//        cmsService.updateCustomer(customer);
//        
//        System.out.println("Get Customer By ID...");
//        customer = cmsService.getCustomerById(26);
//        System.out.println("Customer ID :" +customer.getCustId());
//        System.out.println("Customer First Name :" +customer.getFirstName());
//        System.out.println("Customer Last Name :" +customer.getLastName());
    }
}