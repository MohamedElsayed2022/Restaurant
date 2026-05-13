package com.coding.resturant.springsecurity.security;

import com.coding.resturant.dto.UserPrincipal;
import com.coding.resturant.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return Optional.ofNullable(userRepository.findByEmail(userEmail))
                .map(UserPrincipal::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
        // UserPrincipal userPrincipal = new UserPrincipal(user);

    }
}
