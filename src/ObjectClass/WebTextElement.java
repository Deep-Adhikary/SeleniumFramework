package ObjectClass;

import org.openqa.selenium.WebElement;

/**
 * Created by phoenix on 9/7/17.
 */
public class WebTextElement {

    private final WebElement element;
    public WebTextElement(final WebElement element) {
        this.element = element;
    }
    public String getText() {
        return element.getText();
    }
}
