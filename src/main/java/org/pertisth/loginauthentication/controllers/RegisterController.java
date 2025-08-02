package org.pertisth.loginauthentication.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pertisth.loginauthentication.models.ErrorMessage;
import org.pertisth.loginauthentication.models.User;
import org.pertisth.loginauthentication.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    ResponseEntity<?> createUser(@RequestBody User user) {
        try{
            if(user.getUsername() == null || user.getUsername().isEmpty()) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage("Empty username");
                return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
            }
            if(user.getPassword() == null || user.getPassword().isEmpty()) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage("Empty password");
                return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
            }

            User createUser = authService.saveUser(user);
            return new ResponseEntity<>(createUser, HttpStatus.CREATED);

        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getallusers")
    public List<User> getAllUsers() {
        return this.authService.getAllUsers();
    }

    @GetMapping("/getcsrftoken")
    public CsrfToken getCsrfToken(HttpServletRequest http) {
        return (CsrfToken) http.getAttribute("_csrf");
    }
}
