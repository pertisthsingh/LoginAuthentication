package org.pertisth.loginauthentication.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.pertisth.loginauthentication.models.ErrorMessage;
import org.pertisth.loginauthentication.models.User;
import org.pertisth.loginauthentication.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody User user) {
        try{
            if(user.getUsername() == null || user.getUsername().isEmpty()){
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage("Username is empty");
                return ResponseEntity.badRequest().body(errorMessage);
            }
            User userFound = this.authService.getUserByUserName(user.getUsername());
            if(userFound == null) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setMessage("User not found");
                return ResponseEntity.badRequest().body(errorMessage);
            }
            return new ResponseEntity<>(userFound,HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getcsrf")
    public ResponseEntity<CsrfToken> getCsrf(HttpServletRequest request) {
        return ResponseEntity.ok((CsrfToken) request.getAttribute("_csrf"));
    }
}
