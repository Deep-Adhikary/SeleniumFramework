package ObjectClass;

import org.openqa.selenium.*;

import java.util.List;

/**
 * Created by phoenix on 8/7/17.
 */
public class WebTextBox  {
    private final WebElement element;
    public WebTextBox(final WebElement element) {
        this.element = element;
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
}
