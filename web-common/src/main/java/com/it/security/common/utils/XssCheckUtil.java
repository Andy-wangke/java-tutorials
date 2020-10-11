package com.it.security.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 *
 */
public class XssCheckUtil {

    private static final String HTTP_3A = "http%3A//";
    private static final String HTTP = "http://";
    private static final String HTTPS_3A = "https%3A//";
    private static final String HTTPS = "https://";

    /**
     * Make sure these are regular expressions. Example, "[" should be written as "\\["
     */
    public final static String[] ILLEGAL_TAGS_FOR_STRINGS = { // NOSONAR
        /* START TAGS - Taken from AttributeValueMapHelper */
        "<script", "<applet", "<object", "<iframe", "<embed", "<!\\[CDATA\\[", "<\\[\\[",
        /* END TAGS - Taken from AttributeValueMapHelper */
        "</script>", "</applet>", "</object>", "</iframe>", "</embed>", "\\]\\]>", /* "<", ">", "//", */ "javascript:" };

    public final static String[] ILLEGAL_TAGS_FOR_URLS = { // NOSONAR
        /* START TAGS - Taken from AttributeValueMapHelper */
        "<script", "%3Cscript", "Q3cscript", "<applet", "<object", "<iframe", "<embed", "<!\\[CDATA\\[", "<\\[\\[",
        /* END TAGS - Taken from AttributeValueMapHelper */
        "</script>", "script%3E", "scriptQ3e", "</applet>", "</object>", "</iframe>", "</embed>", "\\]\\]>", /*
                                                                                                              * "<", "%3C", "Q3c", ">", "%3E", "Q3e",
                                                                                                              */ "javascript%3A" };

    private final static String[] QUOTES = { "\"", "'" };

    /**
     * Creating this array to avoid recompilation of String regEx over and over again
     */
    private final static Pattern[] ILLEGAL_PATTERN_FOR_STRINGS = createRegExPatterns(ILLEGAL_TAGS_FOR_STRINGS);
    private final static Pattern[] ILLEGAL_PATTERN_FOR_URLS = createRegExPatterns(ILLEGAL_TAGS_FOR_URLS);
    private final static Pattern[] QUOTES_PATTERN = createRegExPatterns(QUOTES);

    private final static String DEFAULT_REPLACE_WITH_FOR_STRINGS = " ";
    private final static String DEFAULT_REPLACE_WITH_FOR_URLS = "";

    /**
     * Checks if the Map contains Xss vulnerable params and cleans-up Xss hacks from them
     * 
     * @param paramMap
     *            Map<String, String[]> parameter map
     */
    public static void removeXssHacks(Map<String, String[]> paramMap, boolean removeQuotes) {
        removeXssHacks(paramMap, false, removeQuotes);
    }

    /**
     * Checks if the Map contains Xss vulnerable params and cleans-up Xss hacks from them
     * 
     * @param paramMap
     *            Map<String, String[]> parameter map
     */
    public static void removeXssHacks(Map<String, String[]> paramMap) {
        removeXssHacks(paramMap, false, false);
    }

    /**
     * Checks if the Map contains Xss vulnerable params and cleans-up Xss hacks from them
     * 
     * @param paramMap
     *            Map<String, String[]> parameter map
     * @param sanitizeString
     *            boolean if true, the string is also HTML escaped
     */
    public static void removeXssHacks(Map<String, String[]> paramMap, boolean sanitizeString, boolean removeQuotes) {
        if (paramMap == null) {
            return;
        }
        for (Entry<String, String[]> e : paramMap.entrySet()) {
            String[] paramValues = e.getValue();
            if (paramValues != null) {
                // Remove illegal strings
                for (int j = 0; j < paramValues.length; j++) {
                    paramValues[j] = removeIllegalCharacters(paramValues[j], sanitizeString, removeQuotes).trim();
                }
                paramMap.put(e.getKey(), paramValues);
            }
        }
    }

    /**
     * Replaces illegal tags/characters in the parameter with " " (String as param) or "" (URL as param, example: _ruu)
     * 
     * @param paramValue
     * @return valid string with no XSS hacks
     */
    public static String removeIllegalCharacters(String paramValue, boolean removeQuotes) {
        return removeIllegalCharacters(paramValue, false, removeQuotes);
    }

    /**
     * Replaces illegal tags/characters in the parameter with " " (String as param) or "" (URL as param, example: _ruu)
     * 
     * @param paramValue
     * @return valid string with no XSS hacks
     */
    public static String removeIllegalCharacters(String paramValue) {
        return removeIllegalCharacters(paramValue, false, false);
    }

    /**
     * Replaces illegal tags/characters in the parameter with " " (String as param) or "" (URL as param, example: _ruu)
     * 
     * @param paramValue
     * @param sanitizeString
     *            boolean if true, the string is also HTML escaped
     * @return valid string with no XSS hacks
     */
    public static String removeIllegalCharacters(String paramValue, boolean sanitizeString, boolean removeQuotes) {
        if (paramValue == null) {
            return null;
        }
        String param = paramValue;
        if (isURL(param)) {
            for (int i = 0; i < ILLEGAL_PATTERN_FOR_URLS.length; i++) {
                param = ILLEGAL_PATTERN_FOR_URLS[i].matcher(param).replaceAll(DEFAULT_REPLACE_WITH_FOR_URLS);
            }
        } else {
            for (int i = 0; i < ILLEGAL_PATTERN_FOR_STRINGS.length; i++) {
                param = ILLEGAL_PATTERN_FOR_STRINGS[i].matcher(param).replaceAll(DEFAULT_REPLACE_WITH_FOR_STRINGS);
            }
            if (removeQuotes) {
                for (int i = 0; i < QUOTES_PATTERN.length; i++) {
                    param = QUOTES_PATTERN[i].matcher(param).replaceAll(DEFAULT_REPLACE_WITH_FOR_STRINGS);
                }
            }
        }
        if (sanitizeString) {
            param = sanitizeString(param);
        }
        return param;
    }

    /**
     * Creates an array of compiled Patterns, which avoids recompilation of the RegEx's over and over again
     * 
     * @param tags
     * @return Array of compiled Pattern objects
     */
    private static Pattern[] createRegExPatterns(String[] tags) {
        Pattern[] patterns = new Pattern[tags.length];
        for (int i = 0; i < tags.length; i++) {
            patterns[i] = Pattern.compile(tags[i], Pattern.CASE_INSENSITIVE);
        }
        return patterns;
    }

    private static boolean isURL(String parameter) {
        if (parameter.startsWith(HTTP) || parameter.startsWith(HTTP_3A) || parameter.startsWith(HTTPS) || parameter.startsWith(HTTPS_3A)) {
            // Not checking for httpQ3a//, such URL won't exist
            return true;
        }
        return false;
    }

    /**
     * <pre>
& --> &amp;
< --> &lt;
> --> &gt;
" --> &quot;
' --> &#x27; &apos; is not recommended
/ --> &#x2F; forward slash is included as it helps end an HTML entity
     * 
     * 
     * </pre>
     * 
     * 
     * <pre>
     * function escapeHTML(unsafe_str) { 
     * return unsafe_str 
     * .replace(/</g, '&lt;') 
     * .replace(/>/g, '&gt;') 
     * .replace(/&/g, '&amp;') 
     * .replace(/\"/g,'&quot;') 
     * .replace(/\'/g, '&#39;') 
     * .replace(/\//g, '&#x2F;') 
     * }
     * </pre>
     * 
     * @param input
     * @return
     */
    public static String escapeHtmlChar(String input) {
        if(input == null || input.length() == 0) {
            return input;
        }
        
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("\"", "&quot;");
        input = input.replaceAll(" ", "&nbsp;");
        input = input.replaceAll("'", "&#x27;");//' --> &#x27;/&#39; | &apos; is not recommended
        return input;
    }
    
    
    public static String decodeUrl(String input) {
        if(input == null || input.length() == 0) {
            return input;
        }
        input = input.replaceAll("%3C", "<");
        input = input.replaceAll("%3E", ">");
        
        return input;
    }   
    
    private static String sanitizeString(String input) {
        StringBuilder strBuf = new StringBuilder();

        char ch;
        int len = input.length();

        for (int i = 0; i < len; i++) {

            ch = input.charAt(i);

            if (ch == '<') {
                strBuf.append("&lt;");
            } else if (ch == '>') {
                strBuf.append("&gt;");
            } else if (ch == '&') {
                strBuf.append("&amp;");
            } else if (ch == '\"') {
                strBuf.append("&quot;");
            } else if (ch == '\'') {
                strBuf.append("&#39;");
            } else
                strBuf.append(ch);
        }
        return strBuf.toString();
    }
}
