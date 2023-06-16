package com.web.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomSuccessHandler.class);
    private final GrantedAuthority adminAuthority = new SimpleGrantedAuthority(
            "ROLE_ADMIN");

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //if redirected from some specific url, need to remove the cachedRequest to force use defaultTargetUrl
        final RequestCache requestCache = new HttpSessionRequestCache();
        final SavedRequest savedRequest = requestCache.getRequest(request, response);
        //System.out.println("authentication : " + authentication.getPrincipal());
        if (!isAdminAuthority(authentication))
        {
        	String targetUrl = super.determineTargetUrl(request, response);
            // this logic is only for demo purpose, please do not use it on production application.
            if(StringUtils.isEmpty(targetUrl) || StringUtils.pathEquals(targetUrl, "/"))
            {
                targetUrl ="/"; // we can build custom logic
            }
            clearAuthenticationAttributes(request);
            LOG.info("Redirecting customer to the following location {} ",targetUrl);
            
            redirectStrategy.sendRedirect(request, response, targetUrl);

            //You can let Spring security handle it for you.
            // super.onAuthenticationSuccess(request, response, authentication);
        }
        else{
            // we invalidating the session for the admin user.
            invalidateSession(request, response);
        }
        clearAuthenticationAttributes(request);
    }
	
    protected void invalidateSession(final HttpServletRequest request, final HttpServletResponse response) throws IOException
    {
        SecurityContextHolder.getContext().setAuthentication(null);
        request.getSession().invalidate();
        redirectStrategy.sendRedirect(request, response, "/admin");
    }

    protected boolean isAdminAuthority(final Authentication authentication)
    {
        return CollectionUtils.isEmpty(authentication.getAuthorities())
                && authentication.getAuthorities().contains(adminAuthority);
    }
    
}
