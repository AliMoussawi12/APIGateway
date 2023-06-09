package com.example.pregateway.configuration;

import com.example.pregateway.bean.EndUserBeanSession;
import org.aspectj.lang.annotation.Aspect;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;


@KeycloakConfiguration
@EnableGlobalMethodSecurity( securedEnabled = true,prePostEnabled = true)
@Import({KeycloakSpringBootConfigResolver.class})
@Aspect
public class KeycloakAdapterConfig extends KeycloakWebSecurityConfigurerAdapter {


    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    /**
     * Defines the session authentication strategy.
     */
    @Bean
    @Override
    protected NullAuthenticatedSessionStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/customer/**").hasRole("admin")
                .antMatchers("/hello-world/**").hasRole("endUser")
                .antMatchers("/analytics/**").hasRole("endUser")
                .antMatchers("/killbill/**").hasRole("endUser")
                .antMatchers("/proxy/**").hasRole("endUser")
                .antMatchers("/user-**").hasRole("endUser")


                .antMatchers("/authentication/**").permitAll();

    }
}
