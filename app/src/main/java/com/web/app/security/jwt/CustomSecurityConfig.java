package com.web.app.security.jwt;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;
import com.web.app.user.UserSecurityService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor

public class CustomSecurityConfig {

	/*
	// 인증되지 않은 사용자 접근에 대한 handler
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
    	 return authenticationConfiguration.getAuthenticationManager();
    }
    
    
	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		return http
				.httpBasic().disable()
				.formLogin().disable()
				.csrf().disable()
				.cors().configurationSource(corsConfigurationSource())
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeHttpRequests()
				.antMatchers("/css/**", "/fontawesome/**", "/images/**", "/js/**", "/favicon.ico").permitAll()
				.antMatchers("/", "/formlogin", "/jwtLogin", "/jwt/createToken").permitAll()
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
				.build();

	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {   
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.addAllowedOrigin("http://localhost:3000/");
	    configuration.addAllowedOrigin("http://localhost:9000/");
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	    //configuration.setAllowCredentials(true);
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "TOKEN_ID", "X-Requested-With", "Authorization", "Content-Type", "Content-Length", "Cache-Control"));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);

	    return source;
	}
	*/
}
