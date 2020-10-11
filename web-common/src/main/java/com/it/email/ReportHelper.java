package com.it.email;

import java.io.StringWriter;
import java.util.Properties;
import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.dom4j.Document;
import org.dom4j.io.DocumentSource;
import org.slf4j.Logger;

public class ReportHelper {

    private static final Logger s_logger = org.slf4j.LoggerFactory.getLogger(ReportHelper.class);

    public static String xml2Html(Document document, String xsltFilePath) {
        String htmlResult = "";
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
            factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
            StreamSource xsl = new StreamSource(xsltFilePath);
            Transformer transformer = factory.newTransformer(xsl);
            Properties props = transformer.getOutputProperties();
            props.setProperty(OutputKeys.ENCODING, "UTF-8");
            props.setProperty(OutputKeys.METHOD, "html");
            props.setProperty(OutputKeys.VERSION, "4.0");

            transformer.setOutputProperties(props);
            DocumentSource docSource = new DocumentSource(document);
            StringWriter strWriter = new StringWriter();
            StreamResult docResult = new StreamResult(strWriter);
            transformer.transform(docSource, docResult);
            htmlResult = strWriter.toString();
        } catch (Exception e) {
            s_logger.error("Exception occurred while parsing xsl...", e);
        }
        return htmlResult;
    }

    public static void sendReport() {
        // get XML report using Dom4J

        // xml2html
        String content = "";
        // send email
        MailSenderInfo mailInfo = MailHelper.generateMailInfo(false, "Report Name");
        mailInfo.setContent(content);
        SimpleMailSender.sendHtmlMail(mailInfo);
    }
}
