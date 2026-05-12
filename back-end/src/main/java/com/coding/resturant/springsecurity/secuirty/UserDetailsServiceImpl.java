package com.coding.resturant.springsecurity.secuirty;

import com.coding.resturant.dto.UserPrincipal;
import com.coding.resturant.model.User;
import com.coding.resturant.repository.UserRepository;
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
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(userEmail);
        return new UserPrincipal(user);
        // UserPrincipal userPrincipal = new UserPrincipal(user);

    }
}
