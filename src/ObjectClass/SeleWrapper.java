package ObjectClass;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

/**
 * Created by phoenix on 13/7/17.
 */
public abstract class SeleWrapper {
    protected  WebElement element=null;
    protected Properties objectProperties =new Properties();
    SeleWrapper(WebElement element){
        this.element=element;
        objectProperties.setProperty("html id", element.getAttribute("id"));
        objectProperties.setProperty("class",element.getAttribute("class"));
        objectProperties.setProperty("visible",Boolean.toString(element.isDisplayed()));
        objectProperties.setProperty("enabled",Boolean.toString(element.isEnabled()));
        objectProperties.setProperty("innertext",element.getText());
        objectProperties.setProperty("innerhtml",element.getAttribute("innerhtml"));
        objectProperties.setProperty("outerhtml",element.getAttribute("outerhtml"));

    }
    public  String getObjetProperty(String key){
        return objectProperties.getProperty(key);
    }
    public  Properties getObjectProperties(String... keys){
        Properties subproperties=new Properties();
        for (String key:keys) {
            if(objectProperties.containsKey(key.toLowerCase())){
                subproperties.setProperty(key.toLowerCase(),objectProperties.getProperty(key.toLowerCase()));
            }
        }
        return subproperties;
    }
    public abstract boolean exists(int waitTime);
}
