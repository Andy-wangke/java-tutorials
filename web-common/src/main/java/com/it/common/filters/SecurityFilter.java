package com.it.common.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import org.apache.commons.lang.StringUtils;
import org.owasp.esapi.ESAPI;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ebay.kernel.logger.LogLevel;
import com.ebay.kernel.logger.Logger;

public class SecurityFilter extends OncePerRequestFilter {

    private final static String HTTPS_SCHEME = "https";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException,
        IOException {
        try {
            setSecurityHeaderIfNotExists(response, "X-Content-Type-Options", "nosniff");
            setSecurityHeaderIfNotExists(response, "X-XSS-Protection", "1; mode=block");
            if (HTTPS_SCHEME.equalsIgnoreCase(request.getScheme())) {
                response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
            }
        } finally {
            filterChain.doFilter(new SecurityHttpServletRequestWrapper(request), response);
        }
    }

    private static void setSecurityHeaderIfNotExists(HttpServletResponse response, String header, String value) {

        if (!response.containsHeader(header)) {
            response.setHeader(header, value);
        }
    }

    public class SecurityHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private final Logger s_logger = Logger.getInstance(SecurityHttpServletRequestWrapper.class);
        protected Set<Pattern> whitelistPatterns = new HashSet<Pattern>();

        public SecurityHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        private HttpServletRequest getHttpServletRequest() {
            return (HttpServletRequest) super.getRequest();
        }

        @Override
        public String getParameter(String name) {
            String originalInput = getHttpServletRequest().getParameter(name);
            String cleanInput = null;
            try {
                cleanInput = this.getValidInputAndCanonicalize("HTTP parameter name:" + name, originalInput, "HTTPParameterValue", Integer.MAX_VALUE);
            } catch (ValidationException e) {
                s_logger.log(LogLevel.WARN, "Skipping bad paramete." + e.getLogMessage());
            }

            return cleanInput;
        }

        /**
         * allow to get the raw parameter
         * 
         * @param name
         * @return
         */
        public String getRawParameter(String name) {
            return getHttpServletRequest().getParameter(name);
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] paramValues = getHttpServletRequest().getParameterValues(name);
            List<String> newValues = null;
            if (paramValues == null || paramValues.length == 0) {
                return null;
            }
            newValues = new ArrayList<String>();
            for (String value : paramValues) {
                String cleanValue;
                try {
                    cleanValue = this.getValidInputAndCanonicalize("HTTP parameter name:" + name, value, "HTTPParameterValue", Integer.MAX_VALUE);
                    newValues.add(cleanValue);
                } catch (ValidationException e) {
                    s_logger.log(LogLevel.WARN, "Skipping bad parameter." + e.getLogMessage());
                }
            }
            return newValues.toArray(new String[newValues.size()]);
        }

        public String getValidInputAndCanonicalize(String context, String input, String patternType, int maxLength) throws ValidationException {
            return getValidInput(context, input, patternType, maxLength, true);
        }

        /**
         * Validates data received from the browser and returns a safe version.
         * 
         * @param context
         * @param input
         * @param patternType
         * @param maxLength
         * @param canonicalize
         * @return
         * @throws ValidationException
         */
        public String getValidInput(String context, String input, String patternType, int maxLength, boolean canonicalize) throws ValidationException {
            // check for empty/null
            if (StringUtils.isBlank(input) || "null".equalsIgnoreCase(input)) {
                return "";
            }
            // fill into whitelist
            Pattern typePattern = ESAPI.securityConfiguration().getValidationPattern(patternType);
            if (typePattern != null) {
                whitelistPatterns.add(typePattern);
            } else {
                throw new IllegalArgumentException("The selected type [" + patternType + "] was not set via the ESAPI validation configuration");
            }
            String cleanStr = null;
            // check length
            if (input.length() > maxLength) {
                input = input.substring(0, input.length() - 1);
                s_logger.log(LogLevel.WARN, "Input Value " + input + " exceeds maximum allowed length of " + maxLength + " by "
                    + (input.length() - maxLength) + " characters.");
            }
            
            // comment it as raptor invoked this function already 
            // String cleanStr = removeIllegalCharactersAndSanitize(input);

            // check whitelist patterns
            checkWhitelist(context, input);
            // canonicalize
            if (canonicalize) {
                cleanStr = ESAPI.encoder().canonicalize(input, false, false);
            } else {
                // skip canonicalization
                cleanStr = input;
            }
            return cleanStr;
        }

        /**
         * checks input against whitelists.
         * 
         * @param context
         * @param input
         * @return
         * @throws ValidationException
         */
        private String checkWhitelist(String context, String input) throws ValidationException {
            // check whitelist patterns
            for (Pattern p : whitelistPatterns) {
                if (!p.matcher(input).matches()) {
                    throw new ValidationException(context + ": Invalid input. Please confirm to regex " + p.pattern(), "Invalid input: context="
                        + context + ", valid pattern=" + p.pattern());
                }
            }
            return input;
        }
        
        //replace illegal tags/characters in the parameter
        private String removeIllegalCharactersAndSanitize(String inputStr) {
            return CSAdminXssCheckUtil.removeIllegalCharacters(inputStr, true);
        }
    }

    /**
     * validation exception
     */
    class ValidationException extends Exception {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        protected String logMessage = null;

        public String getLogMessage() {
            return logMessage;
        }

        protected ValidationException() {
        }

        public ValidationException(String userMessage, String logMessage) {
            super(userMessage);
            this.logMessage = logMessage;
        }
    }
}

