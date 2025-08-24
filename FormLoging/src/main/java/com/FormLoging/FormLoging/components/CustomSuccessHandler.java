package com.FormLoging.FormLoging.components;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    private static Logger logger  = LoggerFactory.getLogger(CustomSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl ="/";

        Collection<? extends GrantedAuthority> authority = authentication.getAuthorities();

        for(GrantedAuthority r :authority){
            logger.info("this is role {}",r.getAuthority());
            if(r.getAuthority().equalsIgnoreCase("ADMIN")){
                redirectUrl="/admin/get";
                break;
            }
            else{
                redirectUrl="/customer/get";
                break;
            }
        }
response.sendRedirect(redirectUrl);
    }
}
