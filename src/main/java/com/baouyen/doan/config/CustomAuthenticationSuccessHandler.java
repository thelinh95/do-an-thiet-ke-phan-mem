package com.baouyen.doan.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN")) {
            setDefaultTargetUrl("/admin/home");
        } else if (roles.contains("PARTNER")) {
            setDefaultTargetUrl("/partner/home");
        } else if (roles.contains("USER")) {
            setDefaultTargetUrl("/user/home");
        } else {
            setDefaultTargetUrl("/accessDenied");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}

