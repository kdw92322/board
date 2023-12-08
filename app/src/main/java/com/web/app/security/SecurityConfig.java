package com.web.app.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true) 
public class SecurityConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/js/**")).permitAll()
                .antMatchers("/user/userInfo").authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/user/loginProcess")
                        .successHandler(successHandler())
                        .failureHandler( 
                                new AuthenticationFailureHandler() { 
                                    @Override
                                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                        System.out.println("exception : " + exception.getMessage());
                                        response.sendRedirect("/user/login?error");
                                    }
                                })
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/user/logout")
                        .addLogoutHandler((request, response, authentication) -> {
                            HttpSession session = request.getSession();
                            if (session != null) {
                                session.invalidate();
                            }
                        })
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/user/logoutProcess")
                        .invalidateHttpSession(true))
                .sessionManagement(session -> session
                                .invalidSessionUrl("/")
                )
	        	.csrf().disable() 
	        	.anonymous().disable()
	        	
            ;
        
        return http.build();
    }

	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CustomSuccessHandler successHandler() {
	    return new CustomSuccessHandler();
	}
}
