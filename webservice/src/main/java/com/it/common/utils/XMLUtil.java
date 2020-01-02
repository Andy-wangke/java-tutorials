package com.it.common.utils;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author andywang
 */
public class XMLUtil {

    private static Logger logger = LoggerFactory.getLogger(XMLUtil.class);

    /**
     * parse xml via xpath
     * @param xmlStr
     * @param xpathStr
     * @return
     */
    public static String getValueXPathFromXmlStr(String xmlStr, String xpathStr) {
        String value = "";
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlStr));
            Document doc = builder.parse(is);

            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            XPathExpression expr = xpath.compile(xpathStr);

            Object result = expr.evaluate(doc, XPathConstants.NODESET);
            if (result instanceof NodeList) {
                NodeList nodes = (NodeList) result;
                value = nodes.item(0).getNodeValue();
            } else {
                // TODO
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return value;

    }

    public static void main(String[] args) {

        String xmlStr = "<Preferences><PreferenceGroup name=\"LastOutboundSiteIds\"><Preference name=\"Email\">77</Preference></PreferenceGroup><PreferenceGroup name=\"Chat\"><Preference name=\"ChatInlineSpellCheck\">False</Preference><Preference name=\"LastSelectedChatLocale\">zh-CN</Preference></PreferenceGroup></Preferences>";
        System.out.println(xmlStr);
        System.out.println(getValueXPathFromXmlStr(xmlStr, "//Preference"));
        System.out.println();
    }
}
