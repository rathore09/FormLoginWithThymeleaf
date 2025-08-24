package com.FormLoging.FormLoging.config;

import com.FormLoging.FormLoging.components.CustomSuccessHandler;
import org.hibernate.annotations.Bag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, CustomSuccessHandler handler) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth  -> auth
                        .requestMatchers("/auth/login","/auth/sing").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/customer/**").hasRole("USER")
                        .anyRequest().authenticated())
                        .formLogin(form->
                                form
                                        .loginPage("/auth/login")
                                        .loginProcessingUrl("/auth/login")
                                        .successHandler(handler)
                                        .permitAll()
                        )
                .logout(logout->
                        logout
                                .logoutUrl("/auth/logout")
                                .logoutSuccessUrl("/auth/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll())
                .sessionManagement(sess -> sess
                .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession)
                .invalidSessionUrl("/auth/login?invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/auth/login?expired")
        )
        ;
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
