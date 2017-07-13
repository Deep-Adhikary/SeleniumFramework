package ObjectClass;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by phoenix on 8/7/17.
 */
public class Browser {
    private WebDriver driver;
    private ObjectDescriptionHandler objHandle;
    Browser(String browserName){

        switch (browserName){
            case "ie" :
                driver=new InternetExplorerDriver();
                break;
            case "ffx" :
                driver=new FirefoxDriver();
                break;
            case "gchrome":
                driver=(new ChromeDriver());
        }
    }
    Browser(String browserName, String driverPath){

        switch (browserName){
            case "ie" :
                driver=new InternetExplorerDriver();
                break;
            case "ffx" :
                System.setProperty("webdriver.gecko.driver",driverPath);
                driver=new FirefoxDriver();
                break;
            case "gchrome":
                System.setProperty("webdriver.chrome.driver",driverPath);
                driver=new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        objHandle=new ObjectDescriptionHandler(driver);
    }
    public void navigate(String url){
        if(url!=""){
            driver.get(url);
        }else{
            throw new RuntimeException("No Url Provided");
        }
//        driver.findElement(By.xpath("//input[@id='lst-ib']")).sendKeys("Chagol");

    }
    public String getSource(){
        return driver.getPageSource();
    }
    public WebTextBox getWebTextBox(String...objDescription){

        //WebTextBox objWebEdit=new WebTextBox(driver.findElement(By.xpath(objDescription[0])));
        WebTextBox objWebEdit=new WebTextBox(objHandle.getObject(objDescription));

        return objWebEdit;
    }

    public WebTextElement getWebTextElement(String...objDescription){

        //WebTextBox objWebEdit=new WebTextBox(driver.findElement(By.xpath(objDescription[0])));
        WebTextElement objWebTextElement=new WebTextElement(objHandle.getObject(objDescription));

        return objWebTextElement;
    }

    public void waitForPageLoad() {

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                System.out.println("Current Window State       : "
                        + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                return String
                        .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

}
