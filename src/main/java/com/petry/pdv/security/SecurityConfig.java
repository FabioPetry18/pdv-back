package com.petry.pdv.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	SecurityFilter securityFilter;
	
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
       return http
    	   .csrf((csrf -> csrf.disable()))    
           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
           .authorizeHttpRequests(authorize -> authorize
        		   .requestMatchers(HttpMethod.POST, "/login").permitAll()
        		   .requestMatchers(HttpMethod.POST, "/login/autenticar").permitAll()
        		   .requestMatchers(HttpMethod.GET, "/estoque").hasRole("ADMIN")
        		   .requestMatchers(HttpMethod.GET, "/produto").hasRole("FUNCIONARIO")
        		   .requestMatchers(HttpMethod.POST, "/produto").hasRole("FUNCIONARIO")
				   .requestMatchers(HttpMethod.POST, "/caixa").hasRole("CLIENTE")
				   .requestMatchers(HttpMethod.GET, "/caixa").hasRole("CLIENTE") 
				   .requestMatchers(HttpMethod.POST, "/dono").hasRole("ADMIN")
				   .requestMatchers(HttpMethod.POST, "/dono/acesso").hasRole("ADMIN")
				   .requestMatchers(HttpMethod.POST, "/assinatura").hasRole("ADMIN")
				   .anyRequest().authenticated()
		   )
		   .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
           .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CorsFilter corsFilter() {
    	CorsConfiguration corsConfiguration = new CorsConfiguration();
    	corsConfiguration.setAllowCredentials(true);
    	corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
    	corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
    			"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
    			"Access-Control-Request-Method", "Access-Control-Request-Headers"));
    	corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
    			"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
    	corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    	UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    	urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    	return new CorsFilter(urlBasedCorsConfigurationSource);
    }


}