package com.ok.dvweb.config;

import com.ok.dvweb.util.ConstantsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(ConstantsUtil.Security.WHITE_LIST_OMIT)
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, ConstantsUtil.Security.WHITE_LIST_OMIT).permitAll()
                        .requestMatchers(ConstantsUtil.Security.WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}
