package com.infinira.cms.test;

import com.infinira.cms.util.CMSHelper;

public class CMSHelperTest{
    
    public static void main(String args[]){
        CMSHelperTest helperTest = new CMSHelperTest();
        helperTest.getMessageTestWithValidKey();
        helperTest.getMessageTestWithKeyAsEmpty();
        helperTest.getMessageTestWithKeyAsNull();
        helperTest.getMessageTestWithSingleArgs();
        helperTest.getMessageTestWithMultipleArgs();
        helperTest.getMessageTestWithNoArgs();
        // helperTest.getMessageTestWithArgsAsNull();
        helperTest.getMessageTestWithInvalidKey();
    }
    
    public void getMessageTestWithValidKey(){
        String result = CMSHelper.self().getMessage("Ex.DBResource.conFailed");
        System.out.println("Valid Key Test : " +result);
    }
    
    public void getMessageTestWithKeyAsEmpty(){
        String result = CMSHelper.self().getMessage("");
        System.out.println("Empty Key Test : " +result);
    }
    
    public void getMessageTestWithKeyAsNull(){
        String result = CMSHelper.self().getMessage(null);
        System.out.println("Key As Null Test : " +result);
    }
    
    public void getMessageTestWithSingleArgs(){
        String result = CMSHelper.self().getMessage("Ex.DBResource.conFailed", "dbconfig.properties");
        System.out.println("Single Args Test : " +result);
    }
    
    public void getMessageTestWithMultipleArgs(){
        String result = CMSHelper.self().getMessage("Ex.DBResource.conFailed", "dbconfig.properties", "url", "username");
        System.out.println("Multiple Args Test : " +result);
    }
    
    public void getMessageTestWithNoArgs(){
        String result = CMSHelper.self().getMessage("Ex.DBResource.conFailed");
        System.out.println("No Args Test : " +result);
    }
    
    // public void getMessageTestWithArgsAsNull(){
        // String result = CMSHelper.self().getMessage("Ex.DBResource.conFailed", null);
        // System.out.println("Args As Null Test : " +result);
    // }
    
    public void getMessageTestWithInvalidKey(){
        String result = CMSHelper.self().getMessage("Ex.DBResource.conFailedNotFound");
        System.out.println("Invalid Key Test : " +result);
    }
}