package com.joiest.jpf.common.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyUtil {
	
	private ResourceBundle bundle;

    public static PropertyUtil get(String baseName) {
        ResourceBundle bundle = ResourceBundle.getBundle(baseName);
        return new PropertyUtil(bundle);
    }

    private PropertyUtil(ResourceBundle bundle) {
        this.bundle = bundle;
    }
	
    public String getAsString(String key) {
        try {
            return (String) bundle.getString(key);
        }
        catch (MissingResourceException e) {
            return null;
        }
    }
    
    public int getAsInt(String key) {
        try {
            String propertyValue = getAsString(key);
            return Integer.parseInt(propertyValue);
        }
        catch (Exception e) {
            return 0;
        }
    }

    public long getAsLong(String key) {
        try {
            String propertyValue = getAsString(key);
            return Long.parseLong(propertyValue);
        }
        catch (Exception e) {
            return 0;
        }
    }
}
