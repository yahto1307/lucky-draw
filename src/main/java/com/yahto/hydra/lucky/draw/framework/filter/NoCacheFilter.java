package com.yahto.hydra.lucky.draw.framework.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yahto on 2018/9/10 9:52 PM
 *
 * @author yahto
 */
public class NoCacheFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Pragma", "No-cache");
        httpServletResponse.setHeader("Cache-Control", "No-cache");
        httpServletResponse.setDateHeader("Expires", -1);
        httpServletResponse.setHeader("Cache-Control", "No-store");
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
