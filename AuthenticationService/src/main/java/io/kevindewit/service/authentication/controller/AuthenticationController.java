package io.kevindewit.service.authentication.controller;

import io.kevindewit.service.authentication.model.request.AuthenticationRequest;
import io.kevindewit.service.authentication.model.request.RegisterRequest;
import io.kevindewit.service.authentication.model.response.JwtTokenResponse;
import io.kevindewit.service.authentication.model.response.UserResponse;
import io.kevindewit.service.authentication.service.AuthenticationService;
import io.kevindewit.service.authentication.service.RegisterService;
import io.kevindewit.service.authentication.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final RegisterService registerService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, UserService userService, RegisterService registerService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
        this.registerService = registerService;
    }

    @ApiOperation(
            value = "authenticate",
            tags = {
                    "Guest",
                    "User"
            },
            response = JwtTokenResponse.class
    )
    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(
                authenticationService.generateJwtToken(
                        request.getUsername(),
                        request.getPassword()),
                HttpStatus.OK)
                ;
    }

    @ApiOperation(
            value = "register",
            tags = {
                    "Guest"
            }
    )
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {

        if (registerService.existsEmail(request.getEmail()))
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);

        if (registerService.existsUsername(request.getUsername()))
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);

        registerService.register(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "user",
            tags = {
                    "User"
            },
            response = UserResponse.class
    )
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        UserResponse userResponse = userService.getUserInfo(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
