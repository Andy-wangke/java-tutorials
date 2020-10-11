package com.it.email;

import javax.mail.PasswordAuthentication;

public class SimpleAuthenticator extends javax.mail.Authenticator{
    String userName = null;
    String password = null;

    public SimpleAuthenticator() {
    }

    public SimpleAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
