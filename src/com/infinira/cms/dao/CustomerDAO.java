package com.infinira.cms.dao;

import com.infinira.cms.model.Customer;
import com.infinira.cms.util.CMSHelper;
import com.infinira.cms.util.CMSException;
import com.infinira.cms.util.DBResource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class CustomerDAO{

    private static final String COL_CUST_ID = "custid";
    private static final String COL_FIRST_NAME = "firstname";
    private static final String COL_LAST_NAME = "lastname";

    public static int createCustomer(Customer customer){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String insertQuery = null;
        
        try{
            if(customer == null){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidInputData"));
            }
            con = DBResource.self().getConnection();
            insertQuery = CMSHelper.self().getQuery("db.customer.create");
            if(insertQuery == null || insertQuery.isEmpty()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidInsertQuery"));
            }
            ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(!rs.next()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.generateKeyFailed", insertQuery));
            }
            return rs.getInt(1);
        } catch(CMSException cmsex) {
            throw cmsex;
        } catch(Throwable th) {
            throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.createCustomerFailed", insertQuery), th);
        } finally {
            DBResource.self().closeResources(rs, ps, con);
        }
    }
    
    public static List<Customer> getAllCustomer(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String selectQuery = null;
        
        try{
            con = DBResource.self().getConnection();
            selectQuery = CMSHelper.self().getQuery("db.customer.select");
            if(selectQuery == null || selectQuery.isEmpty()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidSelectQuery"));
            }
            ps = con.prepareStatement(selectQuery);
            rs = ps.executeQuery();
            if(!rs.next()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.getCustomerDetailsFailed", selectQuery));
            }
            List<Customer> customersList = new ArrayList<Customer>();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setCustId(rs.getInt(COL_CUST_ID));
                customer.setFirstName(rs.getString(COL_FIRST_NAME));
                customer.setLastName(rs.getString(COL_LAST_NAME));
                
                customersList.add(customer);
            }
            return customersList;
        } catch(CMSException cmsex) {
            throw cmsex;
        } catch(Throwable th) {
            throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.retrieveCustomerFailed", selectQuery), th);
        } finally {
            DBResource.self().closeResources(rs, ps, con);
        }
    }
    
    public static void deleteCustomer(int custId){
        Connection con = null;
        PreparedStatement ps = null;
        String deleteQuery = null;
        
        try{
            if(custId < 1){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidCustomerId"));
            }
            con = DBResource.self().getConnection();
            deleteQuery = CMSHelper.self().getQuery("db.customer.delete");
            if(deleteQuery == null || deleteQuery.isEmpty()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidDeleteQuery"));
            }
            ps = con.prepareStatement(deleteQuery);
            ps.setInt(1, custId);
            int result = ps.executeUpdate();
            if(result == 0){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.custIdNotFound", deleteQuery));
            }
        } catch(CMSException cmsex) {
            throw cmsex;
        } catch(Throwable th) {
            throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.deleteCustomerFailed", deleteQuery), th);
        } finally {
            DBResource.self().closeResources(null, ps, con);
        }
    }
    
    public static void updateCustomer(Customer customer){
        Connection con = null;
        PreparedStatement ps = null;
        String updateQuery = null;
        
        try{
            if(customer == null){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidInputData"));
            }
            con = DBResource.self().getConnection();
            updateQuery = CMSHelper.self().getQuery("db.customer.update");
            if(updateQuery == null || updateQuery.isEmpty()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidUpdateQuery"));
            }
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setInt(3, customer.getCustId());
            int result = ps.executeUpdate();
            if(result == 0){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.updateCustomerFailed", updateQuery));
            }
        } catch(CMSException cmsex) {
            throw cmsex;
        } catch(Throwable th) {
            throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.updateCustomerFailed", updateQuery), th);
        } finally {
            DBResource.self().closeResources(null, ps, con);
        }
    }
    
    public static Customer getCustomerById(int custId){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String selectByIdQuery = null;
        
        try{
            if(custId < 1){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidCustomerId"));
            }
            con = DBResource.self().getConnection();
            selectByIdQuery = CMSHelper.self().getQuery("db.customer.selectById");
            if(selectByIdQuery == null || selectByIdQuery.isEmpty()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.invalidSelectByIdQuery"));
            }
            ps = con.prepareStatement(selectByIdQuery);
            ps.setInt(1, custId);
            rs = ps.executeQuery();
            if(!rs.next()){
                throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.getCustomerDetailsFailed", selectByIdQuery));
            }
            Customer customer = new Customer();
            customer.setCustId(rs.getInt(COL_CUST_ID));
            customer.setFirstName(rs.getString(COL_FIRST_NAME));
            customer.setLastName(rs.getString(COL_LAST_NAME));
            return customer;
        } catch(CMSException cmsex) {
            throw cmsex;
        } catch(Throwable th) {
            throw new CMSException(CMSHelper.self().getMessage("Ex.CustomerDAO.retrieveCustomerFailed", selectByIdQuery), th);
        } finally {
            DBResource.self().closeResources(rs, ps, con);
        }
    }
}