package com.aasencios.springbootcinelogtmdb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/usuario/registro",
                                "/usuario/login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/usuario/login")
                        .loginProcessingUrl("/usuario/login") // 👈 Esto debe coincidir
                        .defaultSuccessUrl("/peliculas", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/usuario/login?logout")
                        .permitAll()
                );

        return http.build();
    }

}
