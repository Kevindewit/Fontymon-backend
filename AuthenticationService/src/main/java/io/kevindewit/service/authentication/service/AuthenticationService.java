package io.kevindewit.service.authentication.service;

import io.kevindewit.service.authentication.config.jwt.JwtAuthenticationTokenProvider;
import io.kevindewit.service.authentication.model.Role;
import io.kevindewit.service.authentication.model.response.JwtTokenResponse;
import io.kevindewit.service.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtAuthenticationTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, JwtAuthenticationTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtTokenResponse generateJwtToken(String username, String password) {
        return userRepository.findOneByUsername(username)
                .filter(account -> passwordEncoder.matches(password, account.getPassword()))
                .map(account -> {
                    List<String> roles = new ArrayList<String>() {{
                        for (Role role : account.getRoles())
                            add(role.getName().name());
                    }};
                    return new JwtTokenResponse(jwtTokenProvider.createToken(account.getUsername(), roles));
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found!"))
        ;
    }
}
