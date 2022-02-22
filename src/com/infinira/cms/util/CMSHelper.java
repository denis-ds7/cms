package com.infinira.cms.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;

public final class CMSHelper{

    private ResourceBundle rBundle;
    private Map<String, String> cmsQueriesMap;
    private static CMSHelper helper;
    private static final String MSG_BUNDLE_FILE = "CMSMessages";
    private static final String CMS_QUERIES_FILE = "cmsqueries.properties";

    private CMSHelper(){
        init();
    }

    private void init(){
        preparedResourceBundle();
        preparedQuery();
    }
    
    public void preparedResourceBundle(){
        try {
            rBundle = ResourceBundle.getBundle(MSG_BUNDLE_FILE);
            if(rBundle == null){
                throw new RuntimeException("Unable to get Resource Bundle");
            }
        } catch(Throwable th) {
            throw new RuntimeException("Unable to find Resource Bundle file", th);
        }
    }
    
    public void preparedQuery(){
        preparedQuery(CMS_QUERIES_FILE);
    }

    public void preparedQuery(String propsFile){
        InputStream iStream = null;
        try {
            iStream = ClassLoader.getSystemClassLoader().getResourceAsStream(propsFile);
            if(iStream == null){
                throw new RuntimeException(getMessage("Ex.CMSHelper.propsFileNotFound", propsFile));
            }
            Properties cmsQueriesProps = new Properties();
            cmsQueriesProps.load(iStream);
            if(cmsQueriesProps == null){
                throw new RuntimeException(getMessage("Ex.CMSHelper.loadPropertiesFailed", propsFile));
            }
            cmsQueriesMap = new HashMap<String, String>((Map) cmsQueriesProps);
        } catch(Throwable th) {
            throw new RuntimeException(getMessage("Ex.CMSHelper.prepareQueryFailed", propsFile), th);
        } finally {
            if(iStream != null){
                try {iStream.close();} catch(Throwable th) { }
            }
        }
    }
    
    public static CMSHelper self(){
        if(helper == null){
            synchronized(CMSHelper.class){
                if(helper == null){
                    helper = new CMSHelper();
                }
            }
        }
        return helper;
    }

    public String getMessage(String key, Object... args){
        try {
            if(key == null || key.isEmpty()){
                return "Invalid Key";
            }
            if(args == null || args.length == 0){
                return rBundle.getString(key);
            }
            return MessageFormat.format(rBundle.getString(key), args);
        } catch(Throwable th) {
            return "Invalid " +key+ " Key";
        }
    }

    public String getQuery(String key){
        return cmsQueriesMap.get(key);
    }
}
