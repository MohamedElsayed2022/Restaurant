package com.coding.resturant.auth;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        service.register(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid LoginRequest request
    ) throws MessagingException {

        return ResponseEntity.ok(service.login(request));
    }
    @GetMapping("/activate-account")
    public ResponseEntity<?> activate(
            @RequestParam String token
    ) {
        service.activateAccount(token);
        return ResponseEntity.ok("Account activated successfully");
    }
}
