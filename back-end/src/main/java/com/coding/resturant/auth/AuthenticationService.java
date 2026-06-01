package com.coding.resturant.auth;
import com.coding.resturant.email.EmailService;
import com.coding.resturant.email.EmailTemplateName;
import com.coding.resturant.user.User;
import com.coding.resturant.user.AuthoritiesRepository;
import com.coding.resturant.user.UserRepository;
import com.coding.resturant.config.security.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService  jwtService;
    private final AuthoritiesRepository authoritiesRepository;
    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;


    @Transactional
    public void register(RegistrationRequest request) throws MessagingException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        var userRole = authoritiesRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("Role User was not initialized"));
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(0)
                .authorities(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
        System.out.println("REGISTER HIT");

    }

    public AuthenticationResponse login(LoginRequest request) throws MessagingException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        UserDetails user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder()
                .token(jwtToken).build();
    }
    @Transactional
    public void activateAccount(String token) {

        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        User user = savedToken.getUser();

        user.setActive(1);

        userRepository.save(user);

        tokenRepository.delete(savedToken);
    }
    private void sendValidationEmail(User user) throws MessagingException {

        var newToken = generateAndSaveActivation(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl + "?token=" + newToken,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivation(User user) {
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String chars = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder codeBuilder = new StringBuilder();
        for (int j = 0; j < length; j++) {
            int randomIndex = random.nextInt(chars.length());
            codeBuilder.append(chars.charAt(randomIndex));
        }
        return codeBuilder.toString();

    }


}
