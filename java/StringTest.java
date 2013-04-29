package bx.test;

import java.util.regex.Pattern;

/**
 * String testing class
 * 
 * @author kxt
 */
public class StringTest {

    /**
     * Valid if a string is numeric or not by using java api
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric1(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        int i = str.length();
        while (i > 0) {
            if (!Character.isDigit(str.charAt(--i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Valid if a string is numeric or not by using regex
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric2(String str) {
        return (str == null || str.trim().length() == 0) ? false : Pattern.matches("[0-9]*", str);
    }
    
    /**
     * Valid if a string is numeric or not by using ascii code
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric3(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        int i = str.length();
        int c;
        while (i > 0) {
            c = str.charAt(--i);
            if (c < 48 || c > 57) {
                return false;
            }
        }
        return true;
    }
    
}
