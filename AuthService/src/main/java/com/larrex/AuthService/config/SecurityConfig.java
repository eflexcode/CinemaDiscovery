package com.larrex.AuthService.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

//    private final String[] publicUrls = {"v1/register", "v1/login", "v1/verify_email_token", "v1/verify_email_toke_expired"};
    private final String[] publicUrls = {"v1/**"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//@Bean
//public AuthenticationProvider authenticationProvider(){
//    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//    daoAuthenticationProvider.authenticate()
//}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(publicUrls).permitAll().anyRequest().permitAll())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }


}
