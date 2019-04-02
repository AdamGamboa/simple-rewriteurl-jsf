package com.orthanc.commons.rewriteurl.jsf;

import java.io.IOException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adam M. Gamboa G
 */
@WebFilter(urlPatterns = {"/*"}, asyncSupported = true)
public class RewriteURLFilter implements Filter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RewriteURLFilter.class);
    private Map<String,String> URLS;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Initializing the RewriteURLFilter class");
        URLS = RewrittenURLs.getInstance().getURLs();
        
        if(URLS != null && !URLS.isEmpty()){
            LOGGER.info("URLs to be rewritten have been detected");
            URLS.entrySet().stream().forEach((entry) -> {
                LOGGER.info(entry.getKey()+"->"+entry.getValue());
            });
        }else{
            LOGGER.info("No URLs to be rewritten have been detected");
        }
    }
    
    @Override
    public void destroy() {
        LOGGER.info("Destroying the RewriteURLFilter class");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper((HttpServletRequest)request);
        String original = wrapper.getRequestURI().substring(wrapper.getContextPath().length());
        
        String originalURLWithoutParam;
        String originalParams;
        int indexOfParam = original.indexOf('?');
        if(indexOfParam != -1){
            originalURLWithoutParam = original.substring(indexOfParam);
            originalParams = original.substring(0, indexOfParam);
        }else{
            originalURLWithoutParam = original;
            originalParams ="";
        }
        
        if(URLS.containsKey(originalURLWithoutParam)) {
            String prettyUrl = URLS.get(originalURLWithoutParam)+originalParams;
            RequestDispatcher dispatcher = wrapper.getRequestDispatcher(prettyUrl);
            dispatcher.forward(request, response);
        } else {
            chain.doFilter(wrapper, response);
        }
    }
}
