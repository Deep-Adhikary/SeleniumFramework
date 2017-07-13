package ObjectClass;

import org.openqa.selenium.*;

import java.util.List;
import java.util.Properties;

/**
 * Created by phoenix on 8/7/17.
 */
public class WebTextBox extends SeleWrapper  {
    public WebTextBox(final WebElement element) {
        super(element);

    }
    public void click() {
        element.click();
    }
    public void sendKeys(CharSequence... charSequences) {
        element.sendKeys(charSequences);
    }
    public void clear() {
        element.clear();
    }

    @Override
    public Properties getObjectProperties(String... keys) {
        return null;
    }

    @Override
    public boolean exists(int waitTime) {
        return false;
    }

}
