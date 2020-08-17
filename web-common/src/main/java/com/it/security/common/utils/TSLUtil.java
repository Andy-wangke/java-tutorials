package com.it.security.common.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class TSLUtil {
    // create a SSLContext for TLSv1.2 protocol
    private static SSLContext createSSLContext() {
        SSLContext sslContext = null;

        try {
            // key Store
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            InputStream keystoreInputStream = new FileInputStream(keyStoreLocation);
            try {
                keyStore.load(keystoreInputStream, (keyStorePassword == null) ? null : keyStorePassword.toCharArray());
            } finally {
                keystoreInputStream.close();
            }
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(keyManagerAlgorithm);
            keyManagerFactory.init(keyStore, (keyManagerPassword == null) ? null : keyManagerPassword.toCharArray());
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();

            // trust Store
            KeyStore trustStore = KeyStore.getInstance(trustStoreType);
            InputStream truststoreInputStream = new FileInputStream(trustStoreLocation);
            try {
                trustStore.load(truststoreInputStream, (trustStorePassword == null) ? null : trustStorePassword.toCharArray());
            } finally {
                truststoreInputStream.close();
            }
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(trustManagerAlgorithm);
            trustManagerFactory.init(trustStore);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            sslContext = SSLContext.getInstance("TSLv1.2");
            sslContext.init(keyManagers, trustManagers, new SecureRandom());
        } catch (Exception e) {
            // replace with proper logging of exception
            e.printStackTrace();
        }
        return sslContext;
    }

    // Initialize
    private static final String keyStoreLocation = getKeyStoreLocation();
    private static final String keyStoreType = getKeyStoreType();
    private static final String keyStorePassword = getKeyStorePassword();
    private static final String keyManagerAlgorithm = getKeyManagerAlgorithm();
    private static final String keyManagerPassword = getKeyManagerPassword();
    private static final String trustStoreLocation = getTrustStoreLocation();
    private static final String trustStoreType = getTrustStoreType();
    private static final String trustStorePassword = getTrustStorePassword();
    private static final String trustManagerAlgorithm = getTrustManagerAlgorithm();

    private static String getTrustStoreType() {
        return System.getProperty("javax.net.ssl.trustStoreType", KeyStore.getDefaultType());
    }

    private static String getKeyStorePassword() {
        return System.getProperty("javax.net.ssl.keyStorePassword", "changeit");
    }

    private static String getKeyStoreLocation() {
        String keyStoreLocation = System.getProperty("javax.net.ssl.keyStore");
        if (keyStoreLocation == null) {
            keyStoreLocation = System.getProperty("java.home") + "/lib/security/cacerts";
        }
        return keyStoreLocation;
    }

    private static String getTrustManagerAlgorithm() {
        String trustManagerAlgorithm = Security.getProperty("ssl.TrustManagerFactory.algorithm");
        if (trustManagerAlgorithm == null) {
            trustManagerAlgorithm = "SunX509";
        }
        return trustManagerAlgorithm;
    }

    private static String getTrustStorePassword() {
        return System.getProperty("javax.net.ssl.trustStorePassword", "changeit");
    }

    private static String getTrustStoreLocation() {
        String trustStoreLocation = System.getProperty("javax.net.ssl.trustStore");
        if (trustStoreLocation == null) {
            trustStoreLocation = getKeyStoreLocation();
        }
        return trustStoreLocation;
    }

    private static String getKeyManagerPassword() {
        return System.getProperty("javax.net.ssl.keyStorePassword", "changeit");
    }

    private static String getKeyManagerAlgorithm() {
        String keyManagerAlgorithm = Security.getProperty("ssl.KeyManagerFactory.algorithm");
        if (keyManagerAlgorithm == null) {
            keyManagerAlgorithm = "SunX509";
        }
        return keyManagerAlgorithm;
    }

    private static String getKeyStoreType() {
        return System.getProperty("javax.net.ssl.keyStoreType", KeyStore.getDefaultType());
    }
}
