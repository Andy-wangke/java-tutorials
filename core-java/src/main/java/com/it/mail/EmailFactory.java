package com.it.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

/**
 * 
 * This class is designed to send email.
 * 
 * @author jian.he3@hp.com
 * 
 */
public class EmailFactory {
	private static final Logger logger = Logger.getLogger(EmailFactory.class);
	private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EmailFactory.class);

	public static MultiPartEmail createEmailAttachment() {
		MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp3.hpe.com");
        email.setSmtpPort(25);
        // logger.debug("email smtp server : " + ""PortalConstants.SMTP_ADDRESS);
        // logger.debug("email smtp server port : " + PortalConstants.PORT);
		try {
			email.setFrom("sqadmin_it@hp.com");
			email.setSubject("Search results from Partner UI");
		} catch (EmailException e) {
			log.error(e);
		}
		return email;
	}
}
