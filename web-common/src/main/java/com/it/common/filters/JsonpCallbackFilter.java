package com.it.common.filters;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * Adds padding to json responses when the 'jsonp' parameter is specified. 
 * 
 */
public class JsonpCallbackFilter implements Filter {

    /**
     * The querystring parameter that indicates the response should be padded
     */
    public static final String CALLBACK_PARAM = "callback";

    /**
     * The regular expression to ensure that the callback is safe for display to a browser
     */
    public static final Pattern SAFE_PATTERN = Pattern.compile("[a-zA-Z0-9_\\.]+");

    /**
     * The default padding to use if the specified padding contains invalid characters
     */
    public static final String DEFAULT_CALLBACK = "handleMatterhornData";

    /**
     * The content type for jsonp is "application/x-javascript", not "application/json".
     */
    public static final String JS_CONTENT_TYPE = "application/x-javascript";

    public static final String CHARACTER_ENCODING = "UTF-8";

    /**
     * The post padding, which is always ');' no matter what the pre-padding looks like
     */
    public static final String POST_PADDING = ");";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse originalResponse = (HttpServletResponse) resp;

        // Determine whether the response must be wrapped
        String callbackValue = request.getParameter(CALLBACK_PARAM);

        if (!"GET".equals(request.getMethod().toUpperCase()) || callbackValue == null || callbackValue.isEmpty()) {
            chain.doFilter(request, originalResponse);
        } else {
            // Ensure the callback value contains only safe characters
            if (!SAFE_PATTERN.matcher(callbackValue).matches()) {
                callbackValue = DEFAULT_CALLBACK;
            }

            // Write the padded response
            String preWrapper = callbackValue + "(";

            HttpServletResponseContentWrapper wrapper = new HttpServletResponseContentWrapper(originalResponse, preWrapper);
            chain.doFilter(request, wrapper);
            
            wrapper.flushWrapper();

        }
    }

    /**
     * A response wrapper that allows for json padding
     */
    static class HttpServletResponseContentWrapper extends HttpServletResponseWrapper {

        protected ByteArrayServletOutputStream buffer;
        protected PrintWriter bufferWriter;
        protected boolean committed = false;
        protected boolean enableWrapping = false;
        protected String preWrapper;

        public HttpServletResponseContentWrapper(HttpServletResponse response, String preWrapper) {
            super(response);

            this.preWrapper = preWrapper;
            this.buffer = new ByteArrayServletOutputStream();
        }

        public void flushWrapper() throws IOException {
            if (enableWrapping) {
                if (bufferWriter != null)
                    bufferWriter.close();

                if (buffer != null)
                    buffer.close();

                byte[] content = wrap(buffer.toByteArray());

                getResponse().setContentType(JS_CONTENT_TYPE);
                getResponse().setContentLength(content.length);
                getResponse().setCharacterEncoding(CHARACTER_ENCODING);
                getResponse().getOutputStream().write(content);
                getResponse().flushBuffer();

                committed = true;
            }
        }

        public byte[] wrap(byte[] content) throws IOException {
            StringBuilder sb = new StringBuilder(preWrapper);
            sb.append(new String(content, CHARACTER_ENCODING));
            sb.append(POST_PADDING);
            return sb.toString().getBytes(CHARACTER_ENCODING);
        }

        @Override
        public String getContentType() {
            return enableWrapping ? JS_CONTENT_TYPE : getResponse().getContentType();
        }

        /**
         * If the content type is set to JSON, we enable wrapping. Otherwise, we leave it disabled.
         */
        @Override
        public void setContentType(String type) {
            enableWrapping = "application/json; charset=UTF-8".equals(type);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            return enableWrapping ? buffer : getResponse().getOutputStream();
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            if (enableWrapping) {
                if (bufferWriter == null) {
                    bufferWriter = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
                }
                return bufferWriter;
            } else {
                return getResponse().getWriter();
            }
        }

        @Override
        public void setBufferSize(int size) {
            if (enableWrapping) {
                buffer.enlarge(size);
            } else {
                getResponse().setBufferSize(size);
            }
        }

        @Override
        public int getBufferSize() {
            return enableWrapping ? buffer.size() : getResponse().getBufferSize();
        }

        @Override
        public void flushBuffer() throws IOException {
            if (!enableWrapping)
                getResponse().flushBuffer();
        }

        @Override
        public boolean isCommitted() {
            return enableWrapping ? committed : getResponse().isCommitted();
        }

        @Override
        public void reset() {
            getResponse().reset();
            buffer.reset();
        }

        @Override
        public void resetBuffer() {
            getResponse().resetBuffer();
            buffer.reset();
        }

    }

    /**
     * Servlet outputStream with buffer
     */
    static class ByteArrayServletOutputStream extends ServletOutputStream {

        protected byte buf[];
        protected int count;

        public ByteArrayServletOutputStream() {
            this(32);
        }

        public ByteArrayServletOutputStream(int size) {
            if (size < 0)
                throw new IllegalArgumentException("Negative intial size:" + size);
            buf = new byte[size];
        }

        public synchronized byte toByteArray()[] {
            return Arrays.copyOf(buf, count);
        }

        public synchronized void reset() {
            count = 0;
        }

        public synchronized int size() {
            return count;
        }

        public void enlarge(int size) {
            if (size > buf.length) {
                buf = Arrays.copyOf(buf, Math.max(buf.length << 1, size));
            }
        }

        @Override
        public synchronized void write(int b) throws IOException {
            int newCount = count + 1;
            enlarge(newCount);
            buf[count] = (byte) b;
            count = newCount;

        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
