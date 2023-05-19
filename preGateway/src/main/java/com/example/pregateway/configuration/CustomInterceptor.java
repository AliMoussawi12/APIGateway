package com.example.pregateway.configuration;

import com.example.pregateway.bean.EndUserBeanSession;
import com.example.pregateway.repository.CustomerInfoRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomInterceptor implements HandlerInterceptor {
    @Autowired
    CustomerInfoRepository customerInfoRepository;
    @Autowired
    EndUserBeanSession endUserBeanSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Check if user has endUser role and token is valid
        if (request.isUserInRole("endUser") && SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<RefreshableKeycloakSecurityContext> keycloakPrincipal =
                    (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (keycloakPrincipal.getKeycloakSecurityContext().getToken().isActive()) {
                endUserBeanSession.setCustomerId(customerInfoRepository.findByUserEmail(keycloakPrincipal.getKeycloakSecurityContext().getToken().getEmail()).getCustomerAccountId());
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Do nothing
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Do nothing
    }
}
