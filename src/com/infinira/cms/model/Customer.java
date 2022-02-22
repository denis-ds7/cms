package com.infinira.cms.model;

public class Customer{

    private int custId; 
	private String firstName;
	private String lastName;
    
    public Customer(){ }
    
    public Customer(String firstName, String lastName){
        if(firstName == null || firstName.isEmpty()){
            throw new RuntimeException("Invalid First Name");
        }
        this.firstName = firstName;
        if(lastName == null || lastName.isEmpty()){
            throw new RuntimeException("Invalid Last Name");
        }
        this.lastName = lastName;
    }
	
	public int getCustId(){
		return custId;
	}
    
	public void setCustId(int custId){
		this.custId = custId;
	}
    
	public String getFirstName(){
		return firstName;
	}
    
	public void setFirstName(String firstName){
        if(firstName == null || firstName.isEmpty()){
            throw new RuntimeException("Invalid First Name");
        }
		this.firstName = firstName;
	}
    
	public String getLastName(){
		return lastName;
	}
    
	public void setLastName(String lastName){
        if(lastName == null || lastName.isEmpty()){
            throw new RuntimeException("Invalid Last Name");
        }
		this.lastName = lastName;
	}
}