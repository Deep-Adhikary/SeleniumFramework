package SeleStudioUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;
/**
 * Created by phoenix on 9/7/17.
 */
public class RegExHelper {
    private static Pattern pat;
    private static Matcher match;
    public static boolean regExTest(String pattarn, String test){
        pat=Pattern.compile(pattarn,Pattern.CASE_INSENSITIVE);
        match=pat.matcher(test);
        if(match.find()){
            return true;
        }else return false;
    }
    public static List<String> getMatches(String pattarn, String test){
        List<String> matches=new ArrayList<String>();
        pat=Pattern.compile(pattarn,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        match=pat.matcher(test);
        while (match.find()) {
            /*System.out.print("Start index: " + match.start());
            System.out.print(" End index: " + match.end() + " ");
            System.out.println(match.group());*/
            matches.add(match.group());
        }
        return matches;
    }
}

