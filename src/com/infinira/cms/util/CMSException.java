package com.infinira.cms.util;

public class CMSException extends RuntimeException{
    
    public CMSException(String exMessage){
        super(exMessage);
    }
    
    public CMSException(String exMessage, Throwable rootCause){
        super(exMessage, rootCause);
    }
}