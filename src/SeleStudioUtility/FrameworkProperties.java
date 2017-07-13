package SeleStudioUtility;

import java.util.Properties;

/**
 * Created by phoenix on 10/7/17.
 */
public class FrameworkProperties {
    private static Properties globalVariables=new Properties();
    public static String getProperty(String key){
        return globalVariables.getProperty(key);
    }
    public static void setProperty(String key,String value){
        globalVariables.setProperty(key,value);
    }
}
