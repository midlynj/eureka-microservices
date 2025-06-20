//package com.example.couponservice.logs;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.util.ContentCachingRequestWrapper;
//import org.springframework.web.util.ContentCachingResponseWrapper;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import static java.util.Collections.list;
//
//@Configuration
//public class LoggingFilterBean extends GenericFilterBean {
//
//    private static final Logger log = LoggerFactory.getLogger(LoggingFilterBean.class);
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        ContentCachingRequestWrapper requestWrapper = requestWrapper(request);
//        ContentCachingResponseWrapper responseWrapper = responseWrapper(response);
//
//
//        chain.doFilter(requestWrapper, responseWrapper);
//
//        logRequest(requestWrapper);
//        logResponse(responseWrapper);
//    }
//
//    private void logRequest(ContentCachingRequestWrapper request) {
//
//        final Map<String, Object> body = new HashMap<>();
//        body.put("Timestamp", getCurrentTimestamp());
//        body.put("Path", request.getServletPath());
//        body.put("Request type:" ,request.getMethod());
//
////        StringBuilder builder = new StringBuilder();
//        log.info("Request: {} " , body);
//
////        builder.append(headersToString(list(request.getHeaderNames()), request::getHeader));
////        builder.append(new String(request.getContentAsByteArray()));
////        builder.append("Path: "+ request.getServletPath());
////        builder.append("Method: "+request.getMethod());
//
////        log.info("Request: {}", builder);
//    }
//
//    private void logResponse(ContentCachingResponseWrapper response) throws IOException {
//
//        final Map<String, Object> body = new HashMap<>();
//        body.put("Timestamp", getCurrentTimestamp());
//        body.put("Status: ", response.getStatus());
//        log.info("Response: {} ", body);
////        StringBuilder builder = new StringBuilder();
////        builder.append(new String(response.getContentAsByteArray()));
////        log.info("Response: {}", builder);
////        response.copyBodyToResponse();
//    }
//
//
//
//    private ContentCachingRequestWrapper requestWrapper(ServletRequest request) {
//        if (request instanceof ContentCachingRequestWrapper requestWrapper) {
//            return requestWrapper;
//        }
//        return new ContentCachingRequestWrapper((HttpServletRequest) request);
//    }
//
//    private ContentCachingResponseWrapper responseWrapper(ServletResponse response) {
//        if (response instanceof ContentCachingResponseWrapper responseWrapper) {
//            return responseWrapper;
//        }
//        return new ContentCachingResponseWrapper((HttpServletResponse) response);
//    }
//
//    private String getCurrentTimestamp() {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
//        return now.format(formatter);
//    }
//
//
//}