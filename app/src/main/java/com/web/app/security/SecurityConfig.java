package com.web.app.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	
	private final CorsConfig corsConfig;
	
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/"), new AntPathRequestMatcher("/css/**"), new AntPathRequestMatcher("/images/**"), new AntPathRequestMatcher("/js/**"), new AntPathRequestMatcher("/favicon.ico")).permitAll()
                .antMatchers("/user/userInfo").hasRole("ADMIN")
                .antMatchers().authenticated()
                .and()
                .formLogin(login -> login
                    .loginPage("/")
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
                                    response.sendRedirect("/?error");
                                }
                            })
                    .permitAll())
                .logout()
                	.logoutUrl("/logout") //로그아웃 처리 URL	
                	.deleteCookies("JSESSIONID","Remember-me")//로그아웃 할때, 삭제할 쿠키
                	.logoutSuccessHandler((request, response, authentication) -> response.sendRedirect("/user/logoutProcess")) //로그아웃 성공 후 핸들러, 추가기능이 있을 경우 추가하면 된다.
                	.addLogoutHandler((request, response, authentication) -> { //로그아웃 할 때, 사용자가 지정한 행동을 하고 싶을 때
                		System.out.println("logout Success!!");
                		HttpSession session = request.getSession();
                		session.invalidate();
                	})
                .and()
                .addFilter(corsConfig.corsFilter())
	        	.csrf().disable() 
	        	.anonymous().disable();
        return http.build();
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected CustomSuccessHandler successHandler() {
	    return new CustomSuccessHandler("/");
	}
}