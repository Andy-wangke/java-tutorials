package com.it.email;

import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleMailSender {

    public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
        SimpleAuthenticator authenticator = null;
        Properties properties = mailInfo.getProperties();
        Session sendMailSession = null;
        if (mailInfo.isValidate()) {
            authenticator = new SimpleAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
            sendMailSession = Session.getDefaultInstance(properties);
        } else {
            sendMailSession = Session.getDefaultInstance(properties, authenticator);
        }

        try {
            Message mailMessage = new MimeMessage(sendMailSession);
            Address from = new InternetAddress(mailInfo.getFromAddress());
            mailMessage.setFrom(from);
            String toAddresses = mailInfo.getToAddress();
            String[] toAddressArray = toAddresses.split(",");
            if (toAddressArray == null) {
                return false;
            }
            Address[] addressArray = new Address[toAddressArray.length];
            for (int i = 0; i < toAddressArray.length; i++) {
                addressArray[i] = new InternetAddress(toAddressArray[i]);
            }

            mailMessage.setRecipients(Message.RecipientType.TO, addressArray);
            mailMessage.setSubject(mailInfo.getSubject());
            mailMessage.setSentDate(new Date());
            Multipart mainPart = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            mailMessage.setContent(mainPart);
            Transport.send(mailMessage);

            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
