/**
 * Created by phoenix on 5/7/17.
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.firefox.FirefoxDriver;
public class TestSelenium {
    public static void main(String[] args){
        System.setProperty("webdriver.gecko.driver","//home/phoenix//IdeaProjects//SeleniumFramework//Lib//geckodriver" );
        /*FirefoxDriver driver=new FirefoxDriver();
        SessionId sid=driver.getSessionId();
        System.out.println(sid.toString());
        String url;
        CommandExecutor cexe=driver.getCommandExecutor();

        driver.get("http://demo.guru99.com/");
        WebElement element=driver.findElement(By.xpath("//input[@name='emailid']"));
        element.sendKeys("adhikary33@gmail.com");

        WebElement button=driver.findElement(By.xpath("//input[@name='btnLogin']"));
        button.click();*/
        RWDTest rwtest=new RWDTest();
        try{
            rwtest.setUp();
            rwtest.testSimple();
            rwtest.tearDown();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
