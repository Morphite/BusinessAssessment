package com.diplome.businessassessment.config;

import com.diplome.businessassessment.model.SecurityUserDetails;
import com.diplome.businessassessment.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessAuthenticationHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        User currentUser = ((SecurityUserDetails) authentication.getPrincipal()).getUser();

        httpServletRequest.getSession().setAttribute("user", currentUser);

        httpServletResponse.sendRedirect("/");
    }
}
