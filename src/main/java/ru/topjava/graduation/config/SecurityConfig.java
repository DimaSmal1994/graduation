package ru.topjava.graduation.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("favicon.ico").permitAll()
                .antMatchers("admin/**").hasRole("ADMIN")
                .antMatchers("profile/**").hasRole("USER")
                .antMatchers("/**").authenticated()
                .antMatchers("/login").permitAll().and().formLogin().and().httpBasic();
    }

}
