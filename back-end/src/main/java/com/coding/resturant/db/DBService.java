package com.coding.resturant.db;

import com.coding.resturant.model.Authorities;
import com.coding.resturant.model.User;
import com.coding.resturant.repository.AuthoritiesRepository;
import com.coding.resturant.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DBService implements CommandLineRunner {
    private final AuthoritiesRepository authoritiesRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
/*
      User user = new User();
      user.setEmail("elsayedabdalah4@gmail.com");
      user.setPassword(passwordEncoder.encode("123456789"));
      user.setActive(1);
        List<Authorities> authorities = authoritiesRepository.findAll();
        user.getAuthorities().add(authorities.get(0));
//        user.getAuthorities().add(authorities.get(1));
        userRepository.save(user);

*/
    }
}
