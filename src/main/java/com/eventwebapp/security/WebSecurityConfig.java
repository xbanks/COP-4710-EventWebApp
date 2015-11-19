package com.eventwebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by xavier on 11/12/15.
 */

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("STUDENT");
    }

    // TODO: 11/12/15 maybe do all of this in a spring-security.xml file? might be cleaner
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //.anyRequest().permitAll().and().csrf();
                .antMatchers("/events/**").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/events/create").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/rsos/create").access("hasRole('ROLE_STUDENT')")
                .antMatchers("/events/").permitAll()
                .anyRequest().permitAll()
                .and()
                    .formLogin().loginPage("/login")
                        .failureUrl("/login").permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                .and()
                    .exceptionHandling().accessDeniedPage("/register")
                .and().csrf();
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
