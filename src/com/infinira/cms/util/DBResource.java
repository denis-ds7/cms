package com.infinira.cms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
import java.text.MessageFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBResource{

    private static String url;
    private static String userName;
    private static String password;
    private static DBResource dbResource;
    private static final String DBCONFIG_FILE_NAME = "dbconfig.properties";

    private DBResource(){
        init();
    }

    private void init(){
        InputStream iStream = null;
        try {
            iStream = ClassLoader.getSystemClassLoader().getResourceAsStream(DBCONFIG_FILE_NAME);
            if(iStream == null){
                throw new RuntimeException(getMessage(EX_FILE_NOT_FOUND, DBCONFIG_FILE_NAME));
            }
            Properties properties = new Properties();
            properties.load(iStream);

            url = properties.getProperty("db.url");
            if(url == null || url.isEmpty()){
                throw new RuntimeException(getMessage(EX_INVALID_URL, DBCONFIG_FILE_NAME));
            }
            userName = properties.getProperty("db.username");
            if(userName == null || userName.isEmpty()){
                throw new RuntimeException(getMessage(EX_INVALID_USER_NAME, DBCONFIG_FILE_NAME));
            }
            password = properties.getProperty("db.password");
            if(password == null || password.isEmpty()){
                throw new RuntimeException(getMessage(EX_INVALID_PASSWORD, DBCONFIG_FILE_NAME, userName));
            }
        } catch(Throwable th) {
            throw new RuntimeException(getMessage(EX_VALUES_NOT_FOUND, DBCONFIG_FILE_NAME), th);
        } finally {
            if(iStream != null){
                try {iStream.close();} catch(Throwable th){ }
            }
        }
    }
    
    public static DBResource self(){
        if(dbResource == null){
            synchronized(DBResource.class){
                if(dbResource == null){
                    dbResource = new DBResource();
                }
            }
        }
        return dbResource;
    }

    public Connection getConnection(){
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch(Throwable th) {
            throw new RuntimeException(getMessage(EX_CON_FAILED, url, userName, DBCONFIG_FILE_NAME), th);
        }
    }
    
    public static void closeResources(ResultSet rs, PreparedStatement ps, Connection con){
        if(rs != null){
            try {rs.close();} catch(Throwable th) { }
        }
        if(ps != null){
            try {ps.close();} catch(Throwable th) { }
        }
        if(con != null){
            try {con.close();} catch(Throwable th) { }
        }
    }

    public static String getMessage(String message, Object... args){
        try {
            if(message == null || message.isEmpty()){
                return "Invalid Message";
            }
            if(args == null || args.length == 0){
                return message;
            }
            return MessageFormat.format(message, args);
        } catch (Throwable th) {
            return message;
        }
    }

    private static final String EX_CON_FAILED = "Failed to create connection using ({0}) and ({1}) at ({2})";
    private static final String EX_FILE_NOT_FOUND = "Unable to find ({0}) file";
    private static final String EX_INVALID_URL = "Invalid URL in ({0})";
    private static final String EX_INVALID_USER_NAME = "Invalid Username in ({0})";
    private static final String EX_INVALID_PASSWORD = "Invalid Password in ({0}) file for ({1})";
    private static final String EX_VALUES_NOT_FOUND = "Failed to get values from ({0}) file";
}