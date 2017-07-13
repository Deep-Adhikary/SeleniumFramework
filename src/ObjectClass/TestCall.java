package ObjectClass;

import SeleStudioUtility.FrameworkProperties;
import SeleStudioUtility.RegExHelper;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phoenix on 8/7/17.
 */
public class TestCall {
    public static void main(String[] args) {

        Browser bw=new Browser(BrowserFactory.fireFox(), "Lib/geckodriver");
        bw.navigate("https://stackoverflow.com/questions/4234985/how-to-for-each-the-hashmap");
        bw.waitForPageLoad();

        FrameworkProperties.setProperty("regex","true");
        long before=System.currentTimeMillis();
        System.out.println( bw.getWebTextElement("html tag:=span","class:=comment-copy","innertext:=I can not with such a design.*?" ,"index:=0").getText());
        before=System.currentTimeMillis()-before;
        System.out.println(before);
    }

}
