package com.it.conn.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import com.sun.jersey.client.urlconnection.HttpURLConnectionFactory;
import com.sun.jersey.client.urlconnection.URLConnectionClientHandler;

public class HttpCallUtil {

    private static Logger jutil_logger = Logger.getLogger(HttpCallUtil.class.getName());

    private static final int MAX_RETRY = 3;
    private static String proxy_Host = "c2syubi.vip.ebay.com";
    private static int proxy_Port = 80;
    private static Integer CONNECTION_TIMEOUT = 30000;
    private static String AUTH_USERNAME = "";
    private static String AUTH_PASSWORD = "";

    /**
     * url connection with java native net,supporting retry and proxy
     * 
     * @param endpoint
     * @param headers
     * @param requestXML
     * @param isProxy
     * @return
     */
    public static HttpURLConnection openConnectionForSoap(String endpoint, Map<String, String> headers, String requestXML, boolean isProxy) {
        HttpURLConnection httpCon = null;
        OutputStream os = null;
        int retry = 0;
        while (retry < MAX_RETRY) {
            try {
                URL url = new URL(endpoint);
                // auth
                Authenticator.setDefault(new SimpleAuthenticator(AUTH_USERNAME, AUTH_PASSWORD));
                // proxy
                if (isProxy) {
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy_Host, proxy_Port));
                    httpCon = (HttpURLConnection) url.openConnection(proxy);
                } else {
                    httpCon = (HttpURLConnection) url.openConnection();
                }
                // headers
                if (headers != null) {
                    for (String key : headers.keySet()) {
                        httpCon.setRequestProperty(key, headers.get(key));
                    }
                }
                httpCon.setRequestMethod("POST");
                httpCon.setDoInput(true);
                httpCon.setDoOutput(true);
                httpCon.connect();
                os = httpCon.getOutputStream();
                httpCon.getOutputStream().write(requestXML.getBytes());
            } catch (Exception e) {
                retry++;
                if (MAX_RETRY <= retry) {
                    throw new RuntimeException(e);
                }
            } finally {
                if (os != null) {
                    try {
                        os.flush();
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return httpCon;
    }

    /**
     * @param endpoint
     * @param headers
     * @param isSSLCall
     * @param isProxy
     * @return
     */
    public static String httpclientsWithPost(String endpoint, Map<String, String> headers, boolean isSSLCall, boolean isProxy) {
        // build http clients
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        // ssl call
        if (isSSLCall) {
            SSLContext ctx = SSLContexts.createSystemDefault();
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(ctx, new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" }, null,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpClientBuilder.setSSLSocketFactory(factory);
        }
        // proxy
        RequestConfig requestConfig = null;
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        if (isProxy) {
            HttpHost proxyHost = new HttpHost(proxy_Host, proxy_Port, "http");
            requestConfig = requestConfigBuilder.setConnectTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
                .setProxy(proxyHost)
                .build();

            // proxy credentials
            CredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(new AuthScope(proxyHost), new UsernamePasswordCredentials(AUTH_USERNAME, AUTH_PASSWORD));
            httpClientBuilder.setDefaultCredentialsProvider(provider);
        } else {
            requestConfig = requestConfigBuilder.setConnectTimeout(CONNECTION_TIMEOUT)
                .setSocketTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_TIMEOUT)
                .build();
        }

        CloseableHttpClient client = httpClientBuilder.build();
        CloseableHttpResponse response = null;

        // httppost
        HttpPost request = new HttpPost(endpoint);
        // content type
        try {
            request.setEntity(new StringEntity(HTTP.CONTENT_TYPE, ""));
        } catch (UnsupportedEncodingException e) {// ignore
        }
        /**
         * set url parameters List<NameValuePair> formparams = new ArrayList<NameValuePair>(); for (Entry<String, String> entry : paramMap.entrySet())
         * { formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); } UrlEncodedFormEntity entity = new
         * UrlEncodedFormEntity(formparams, "UTF-8"); httppost.setEntity(entity);
         */
        request.setConfig(requestConfig);
        if (headers != null) {
            for (Map.Entry<String, String> entity : headers.entrySet()) {
                request.addHeader(entity.getKey(), entity.getValue());
            }
        }

        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        int retry = 0;
        boolean isNeedRetry;
        while (retry < MAX_RETRY) {
            isNeedRetry = false;
            try {
                response = client.execute(request);
                if (200 == response.getStatusLine().getStatusCode()) {// OK
                    br = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                retry++;
                isNeedRetry = true;

                if (retry >= MAX_RETRY) {
                    throw new RuntimeException(e);
                }
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (request != null) {
                    request.releaseConnection();
                }
            }
            if (!isNeedRetry)
                break;
        }
        return null;
    }

    // TODO Axis Client proxy

    //Jersey client proxy
    public static String jerseyClientWithPost(String restUrl, MultivaluedMap<String, String> paramMap, Map<String, String> headerMap,
        Object requestBody, String mediaType) throws NoSuchAlgorithmException {
        ClientConfig clientConfig = new DefaultClientConfig();

        // clientConfig.getClasses().add(arg0);
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);

        if (restUrl.startsWith("https")) {
            //set https certificate
            setHttpsCertificate(clientConfig);
        }
        // proxy
        Client client = new Client(new URLConnectionClientHandler(new ProxyFilterFactory(proxy_Host, proxy_Port)), clientConfig);
        client.setConnectTimeout(CONNECTION_TIMEOUT);
        client.setReadTimeout(CONNECTION_TIMEOUT);
        client.addFilter(new LoggingFilter(jutil_logger));

        // params
        WebResource resource = client.resource(restUrl).queryParams(paramMap);

        Builder requestBuilder = resource.getRequestBuilder();
        if (mediaType != null) {
            requestBuilder.type(mediaType);
        }
        // headers
        if (!headerMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                requestBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        ClientResponse response = null;

        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        int retry = 0;
        boolean isNeedRetry;
        while (retry < MAX_RETRY) {
            isNeedRetry = false;
            try {
                if (requestBody == null) {
                    response = requestBuilder.post(ClientResponse.class);
                } else {
                    response = requestBuilder.post(ClientResponse.class, requestBody);
                }
                if (200 == response.getStatus()) {// OK
                    br = new BufferedReader(new InputStreamReader(response.getEntityInputStream(), "utf-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                retry++;
                isNeedRetry = true;

                if (retry >= MAX_RETRY) {
                    throw new RuntimeException(e);
                }
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (response != null) {
                    try {
                        response.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!isNeedRetry)
                break;
        }
        return null;

    }

    private static void setHttpsCertificate(ClientConfig clientConfig) {

        /* Create a trust manager that does not validate certificate chains */
        TrustManager[] tmAllCerts = new TrustManager[] { new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // TODO Auto-generated method stub

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // TODO Auto-generated method stub

            }
        } };
        SSLContext sslContext = null;
        try {
            // create SSLContext for SSL/TSLv1 protocol
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (KeyManagementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        /*
         * Create a HTTPS Properties with defautl Host Verifier which returns true
         */
        clientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(new HostnameVerifier() {

            public boolean verify(String hostname, SSLSession session) {
                // TODO Auto-generated method stub
                return true;
            }
        }, sslContext));

    }

    private static class ProxyFilterFactory implements HttpURLConnectionFactory {

        private String host;
        private int port;

        public ProxyFilterFactory(String host, int port) {
            this.host = host;
            this.port = port;
        }

        @Override
        public HttpURLConnection getHttpURLConnection(URL url) throws IOException {
            URLConnection con = url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port)));
            return (HttpURLConnection) con;
        }
    }

    public static void main(String[] args) {
        // test case
    }
}
