package com.it.conn.utils;

import java.net.PasswordAuthentication;

public class SimpleAuthenticator extends java.net.Authenticator{

    private String user = "";
    private String password = "";

    public SimpleAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password.toCharArray());
    }
}
