package com.web.app.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	private final UserRepository userRepository;
	
	@Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserInfo> _userInfo = this.userRepository.findByUserId(userId);

        if (!_userInfo.isPresent()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        UserInfo userInfo = _userInfo.get();
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("ADMIN".equals(userId)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        return new User(userInfo.getUserId(), userInfo.getPassword(), authorities);
    }
}
