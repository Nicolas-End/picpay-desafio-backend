package com.solution.picpay_problem.security.config;

import com.solution.picpay_problem.security.authentication.TokenFilter;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, TokenFilter tokenFilter) throws Exception{


        return http
            .csrf(csrf-> csrf.disable())
            .cors(cors -> cors.disable())
            .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests((authurize) -> authurize
                .requestMatchers(HttpMethod.GET , "/hello").permitAll()
                .requestMatchers(HttpMethod.POST, "/login", "/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/transfer").hasRole("COMUM")
            )
            .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        /* Essa função Autentifica o usuario por meio das funções presentes no
        * UserDetails, UserDetailsService e a função PasswordEncoder Logo Abaixo*/

        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        /*Diz ao Authentificator qual é o tipo de codificação da senha em questão*/
        return  new BCryptPasswordEncoder();
    }
}
