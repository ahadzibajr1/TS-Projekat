package service.desk.airport.servicedesk.security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.desk.airport.servicedesk.security.dao.UserRepository;
import service.desk.airport.servicedesk.security.dto.AuthCredentials;
import service.desk.airport.servicedesk.security.dto.AuthResponse;
import service.desk.airport.servicedesk.security.dto.ChangePasswordRequest;
import service.desk.airport.servicedesk.security.dto.RegisterRequest;
import service.desk.airport.servicedesk.security.entity.User;
import service.desk.airport.servicedesk.security.service.AuthenticationService;
import service.desk.airport.servicedesk.security.service.JwtService;

import java.io.IOException;

@RestController
@RequestMapping(path="/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /*
    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        var user = authenticationService.getUserByEmail(email);
        if(user == null)
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

        return  ResponseEntity.ok(user);
    }*/


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthCredentials credentials) {

        return ResponseEntity.ok(authenticationService.authenticate(credentials));

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refresh(HttpServletRequest request, HttpServletResponse response) {
        try {
            return ResponseEntity.ok(authenticationService.refreshToken(request,response));
        } catch (IOException e) {
            logger.error("Failed to refresh token");
            return (ResponseEntity<AuthResponse>) ResponseEntity.badRequest();
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        if(jwtService.validateToken(token)) {
            return ResponseEntity.ok("Token valid");
        } else {
            return (ResponseEntity<String>) ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You shall not pass!");
        }
    }

    @PutMapping(path="/change-password")
    public @ResponseBody
    ResponseEntity<String> changePassword(@Valid  @RequestBody ChangePasswordRequest request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        var email = jwtService.extractUsername(token.substring(7));
        if(authenticationService.compareHash(request.getOldPassword(), email)) {
            authenticationService.changePassword(email,request.getNewPassword());
            return  ResponseEntity.ok("Successfully changed password");
        } else {
            return (ResponseEntity<String>) ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("You shall not pass!");
        }
    }

}
