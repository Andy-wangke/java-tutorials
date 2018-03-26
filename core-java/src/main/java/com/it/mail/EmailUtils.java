package com.it.mail;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;


public class EmailUtils {
	private static final Logger logger = Logger.getLogger(EmailUtils.class);
	private final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(EmailUtils.class);
	private Email email;

	private EmailUtils(Email email) {
		this.email = email;
	}

	public EmailUtils addTo(String receiveAddress) throws EmailException {
		logger.debug("add receive address : " + receiveAddress);
		this.email.addTo(receiveAddress);
		return this;
	}

	/**
	 * 
	 * @param attachmentPath
	 *            absolute path for example /temp/test.cvs
	 * @return
	 * @throws EmailException
	 */
	public EmailUtils addAttachment(String attachmentPath) throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		try {
			attachment.setPath(attachmentPath);
			logger.debug("add attachment : " + attachmentPath);
		} catch (Exception e) {
			log.error(e);
		}
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		((MultiPartEmail) email).attach(attachment);
		return this;
	}

	public void send() throws EmailException {
		logger.debug("email informations: ");
		logger.debug("HostName : " + this.email.getHostName());
		logger.debug("Port: " + this.email.getSmtpPort());
		logger.debug("From address : " + this.email.getFromAddress());
		logger.debug("To addresses list: " + this.email.getToAddresses());
		logger.debug("Cc addresses list: " + this.email.getCcAddresses());
		logger.debug("Bcc addresses list: " + this.email.getBccAddresses());
		this.email.send();
	}

	public synchronized static EmailUtils getNewInstance(Email email) {
		return new EmailUtils(email);
	}

}
