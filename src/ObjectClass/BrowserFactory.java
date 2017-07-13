package ObjectClass;

import java.util.Properties;

/**
 * Created by phoenix on 8/7/17.
 */
public class BrowserFactory {
    private Properties browserType;
    BrowserFactory(){
        browserType=new Properties();
        browserType.put("Internet Explorer", "ie");
        browserType.put("Firefox", "ffx");
        browserType.put("Google Chrome", "gchrome");
    }
    public String internetExplorer(){
        return browserType.getProperty("Internet Explorer");
    }
    public String fireFox(){
        return browserType.getProperty("Firefox");

    }
    public String googleChrome(){
        return browserType.getProperty("Google Chrome");
    }
}
