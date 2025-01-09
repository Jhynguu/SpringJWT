package com.example.springjwt.service;

import com.example.springjwt.dto.CustomUserDetails;
import com.example.springjwt.entity.UserEntity;
import com.example.springjwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // DB에서 조회
        UserEntity userData = userRepository.findByUsername(username);

        if (userData == null) {
            // 사용자 정보를 찾지 못했을 때 예외를 던짐
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(userData);
    }
}
