package com.web.app.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authToken = resolveToken(request);
		System.out.println("authToken : " + authToken);
		if(authToken != null && jwtTokenProvider.validateToken(authToken)) {
	        Authentication authentication = jwtTokenProvider.getAuthentication(authToken);
	        SecurityContext context = SecurityContextHolder.getContext();
	        context.setAuthentication(authentication);
	    }
		
		filterChain.doFilter(request, response);
	}
	
	// Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
	
}
