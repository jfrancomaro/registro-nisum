package com.nisumlatam.registro;

import com.nisumlatam.registro.infrastructure.adapter.security.JWTAuthorizationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class RegistroApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistroApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private static final String[] AUTH_WHITELIST = {
                // -- Swagger UI v2
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
        };

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/v1/users/login").permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/h2/**").permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers(AUTH_WHITELIST).permitAll()
                    .antMatchers("/**").authenticated();

            http.headers().frameOptions().disable();
        }
    }
}
