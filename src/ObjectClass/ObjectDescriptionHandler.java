package ObjectClass;

import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import SeleStudioUtility.RegExHelper;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import SeleStudioUtility.FrameworkProperties;
/**
 * Created by phoenix on 8/7/17.
 */
class ObjectDescriptionHandler {
    Object parent;
    WebDriver driver;
    ObjectDescriptionHandler(WebDriver driver){
        this.parent=driver;
    }
    ObjectDescriptionHandler(WebDriver driver,WebElement element){
        this.parent=element;
        this.driver=driver;
    }

    public WebElement getObject(String... description){
        WebElement webEle =null;
        try {
            if(String.valueOf(parent.getClass())=="org.openqa.selenium.WebElement"){
                WebElement objParent= (WebElement) parent;
                switch (description.length){
                    case 0:
                        throw new RuntimeException("Please provide valid arguments");
                    case 1:
                        if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("xpath")){
                            webEle= objParent.findElement(By.xpath(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("cssSelector")){
                            webEle= objParent.findElement(By.cssSelector(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("html id")){
                            webEle= objParent.findElement(By.id(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("class")){
                            webEle= objParent.findElement(By.className(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("linkText")){
                            webEle= objParent.findElement(By.linkText(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("partialLinkText")){
                            webEle= objParent.findElement(By.partialLinkText(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("name")){
                            webEle= objParent.findElement(By.name(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("html tag")){
                            webEle= objParent.findElement(By.tagName(description[0].split(":=")[1]));
                        }
                        else{
                        /*Object Repository Based Approach*/
                            webEle=null;
                            System.out.println("Not Implemented");

                        }
                        break;
                    default:
                        webEle=this.findeElementRegex(objParent,description);
                }
            }else{
                WebDriver objParent= (WebDriver) parent;
                switch (description.length){
                    case 0:
                        throw new RuntimeException("Please provide valid arguments");
                    case 1:
                        if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("xpath")){
                            webEle= objParent.findElement(By.xpath(description[0].split(":=")[1]));
                        }else if(description[0].split(":=")[0].toLowerCase().equalsIgnoreCase("cssSelector")){
                            webEle= objParent.findElement(By.cssSelector(description[0].split(":=")[1]));
                        }else{
                        /*Object Repository Based Approach*/
                            webEle=null;
                            System.out.println("Not Implemented");

                        }
                        break;
                    default:
                        if(FrameworkProperties.getProperty("regex").equalsIgnoreCase("true")){
                            webEle=this.findeElementRegex(objParent,description);
                        }else{
                            String xPath=getXPath(description);
                            //System.out.println(xPath);
                            webEle=objParent.findElement(By.xpath(xPath));
                        }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return webEle;
        }
    }
    @Nullable
    private WebElement findeElementRegex(WebDriver objParent, String... Description) throws Exception {
        Map<String, String> propertyDict=new HashMap<String, String>();
        List<WebElement> eleCol;
        int expIndex;
        int itemIndex=0;
        for ( String desc:Description){
            if(!propertyDict.containsKey(desc.split(":=")[0].toLowerCase())){
                propertyDict.put(desc.split(":=")[0].toLowerCase(),desc.split(":=")[1].toLowerCase());

            }
        }
        if(propertyDict.containsKey("index")){
            expIndex=Integer.parseInt(propertyDict.get("index"));
            propertyDict.remove("index");
        }else expIndex=0;
        //Test Comment
        if (propertyDict.containsKey("html tag")){
            eleCol= objParent.findElements(By.tagName(propertyDict.get("html tag")));
            propertyDict.remove("html tag");
            //System.out.println("After tag "+ eleCol.size());
            if(propertyDict.containsKey("html id")){
                eleCol= eleCol.parallelStream().filter(pele->RegExHelper.regExTest(propertyDict.get("html id"),pele.getAttribute("id"))).collect(Collectors.toList());
               // System.out.println("After id "+ eleCol.size());
            }
            if(propertyDict.containsKey("class")){
                eleCol= eleCol.parallelStream().filter(pele->RegExHelper.regExTest(propertyDict.get("class"),pele.getAttribute("class"))).collect(Collectors.toList());
                //System.out.println("After class "+ eleCol.size());
            }
            if(propertyDict.containsKey("type")){
                eleCol= eleCol.parallelStream().filter(pele->RegExHelper.regExTest(propertyDict.get("type"),pele.getAttribute("type"))).collect(Collectors.toList());
                //System.out.println("After type "+ eleCol.size());
            }
            if(propertyDict.containsKey("name")){
                eleCol= eleCol.parallelStream().filter(pele->RegExHelper.regExTest(propertyDict.get("name"),pele.getAttribute("name"))).collect(Collectors.toList());
                //System.out.println("After name "+ eleCol.size());
            }
            if(propertyDict.containsKey("innertext")){
                eleCol= eleCol.parallelStream().filter(pele->RegExHelper.regExTest(propertyDict.get("innertext"),pele.getText())).collect(Collectors.toList());
               // System.out.println("After innertext "+ eleCol.size());
            }
            if(eleCol.size()>itemIndex){
                return  eleCol.get(itemIndex);
            }else{
                return null;
            }

        }else{
            throw new Exception("Please provide valid html tag");
        }
    }
    @Nullable
    private WebElement findeElementRegex(WebElement objParent, String... Description) throws Exception {
        Map<String, String> propertyDict=new HashMap<String, String>();
        List<WebElement> eleCol;
        int expIndex;
        int itemIndex=0;
        for ( String desc:Description){
            if(!propertyDict.containsKey(desc.split(":=")[0].toLowerCase())){
                propertyDict.put(desc.split(":=")[0].toLowerCase(),desc.split(":=")[1].toLowerCase());

            }
        }
        if(propertyDict.containsKey("index")){
            expIndex=Integer.parseInt(propertyDict.get("index"));
            propertyDict.remove("index");
        }else expIndex=0;

        if (propertyDict.containsKey("html tag")){
            eleCol= objParent.findElements(By.tagName(propertyDict.get("html tag")));
            propertyDict.remove("html tag");
            boolean match=true;
            for (WebElement ele:eleCol) {
                for(Map.Entry<String,String> item: propertyDict.entrySet()){
                    switch (item.getKey()){
                        case "html id":
                            match=match && RegExHelper.regExTest(propertyDict.get("html id"),ele.getAttribute("id"));
                            break;
                        case "type":
                            match=match && RegExHelper.regExTest(propertyDict.get("type"),ele.getAttribute("type"));
                            break;
                        case "name":
                            match=match && RegExHelper.regExTest(propertyDict.get("name"),ele.getAttribute("name"));
                            break;
                        case "innertext":
                            match=match && RegExHelper.regExTest(propertyDict.get("innertext"),ele.getText());
                            break;
                    }
                }
                if(match){
                    return  ele;
                }

            }
        }else{
            throw new Exception("Please provide valid html tag");
        }
        return null;
    }
    private String getXPath(String... description){
        Map<String,String>propertyDict=new HashMap<String,String>();
        String index="[1]";
        for (String desc:description){
            propertyDict.putIfAbsent(desc.split(":=")[0].toLowerCase(),desc.split(":=")[1]);
        }
        StringBuilder sbuilder=new StringBuilder();
        sbuilder.append("//"+propertyDict.get("html tag")+"[");
        propertyDict.remove("html tag");
        if(propertyDict.containsKey("innertext")){
            sbuilder.append("contains(text(), '"+ propertyDict.get("innertext")+"') and ");
            propertyDict.remove("innertext");
        }
        if(propertyDict.containsKey("index")){
            index="["+(Integer.parseInt( propertyDict.get("index"))+1)+"]";
            propertyDict.remove("index");
        }
        for(Map.Entry<String,String> item: propertyDict.entrySet()){
            switch (item.getKey()){
                case "html id":
                    sbuilder.append("@id='" +item.getValue().toLowerCase()+"'");
                    break;
                default:
                    sbuilder.append("@"+ item.getKey() +"='" +item.getValue().toLowerCase()+"'");
                    break;
            }
            sbuilder.append(" and ");
        }
        int indx=sbuilder.lastIndexOf(" and ");
        sbuilder.delete(indx,(indx+" and ".length())-1);
        sbuilder.append("]");
        sbuilder.append(index);
        return sbuilder.toString();

    }
}
