package com.it.email;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class MailHelper {

    private static final String MAIL_CONFIG_FILE = "mail_config.properties";
    
    public static MailSenderInfo generateMailInfo(boolean isErrorMessage,String reportName){
        //loading email properties file
        Properties prop = new Properties();
        try{
            prop.load(MailHelper.class.getResourceAsStream(MAIL_CONFIG_FILE));
        }catch(IOException e){
            e.printStackTrace();
            throw new IllegalArgumentException("load properties file failed: " + e.getMessage());
        }
        MailSenderInfo mailInfo =MailSenderInfo.getIntance();
        mailInfo.setMailServerHost(prop.getProperty(MailSenderInfo.EMAIL_SMTP_HOST));
        mailInfo.setMailServerPort(prop.getProperty(MailSenderInfo.EMAIL_SMTP_PORT));
        mailInfo.setFromAddress(prop.getProperty(MailSenderInfo.EMAIL_FROM_ADDRESS));
        mailInfo.setToAddress(prop.getProperty(MailSenderInfo.EMAIL_DEFAULT_TO_ADDRESS));
        
        Calendar currentDay = Calendar.getInstance();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        
        String subject = String.format(reportName+ " Report %s ", simpleFormat.format(currentDay.getTime()));
        // if error occurs, send error message to admin address
        if (isErrorMessage) {
            mailInfo.setToAddress(prop.getProperty(MailSenderInfo.EMAIL_ADMIN_ADDRESS));
            subject = "Error: " + subject;
        }
        mailInfo.setSubject(subject);
        return mailInfo;
        
    }
}
