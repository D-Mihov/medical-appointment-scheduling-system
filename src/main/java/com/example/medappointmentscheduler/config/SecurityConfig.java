package com.example.medappointmentscheduler.config;

import com.example.medappointmentscheduler.security.CustomAuthenticationFailureHandler;
import com.example.medappointmentscheduler.security.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/signupDoctor", "/patients/**").hasRole("ADMIN")
                .requestMatchers("/add-appointment", "/getAvailableHours/**").hasAnyRole("ADMIN", "PATIENT")
                .requestMatchers("/admin", "/changePassword", "/signupDoctor", "/doctors/**", "/patients/**", "/add-appointment", "/getAvailableHours/**", "/appointments/**").authenticated()
                .requestMatchers("/login", "/signup").anonymous()
                .requestMatchers("/logout", "/css/**", "/js/**", "/img/**", "**/favicon.ico").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureHandler(failureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .httpBasic();
        return http.build();
    }
}
