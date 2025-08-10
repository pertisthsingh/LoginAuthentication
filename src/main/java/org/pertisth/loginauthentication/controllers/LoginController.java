package org.pertisth.loginauthentication.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pertisth.loginauthentication.models.User;
import org.pertisth.loginauthentication.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;
    private AuthenticationManager authenticationManager;
    public LoginController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;

    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user) {
        try{
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if(auth.isAuthenticated()) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Login failed");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getcsrf")
    public ResponseEntity<CsrfToken> getCsrf(HttpServletRequest request) {
        return ResponseEntity.ok((CsrfToken) request.getAttribute("_csrf"));
    }
}
