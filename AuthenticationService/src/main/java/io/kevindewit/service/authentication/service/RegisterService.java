package io.kevindewit.service.authentication.service;

import io.kevindewit.service.authentication.model.ROLE_NAME;
import io.kevindewit.service.authentication.model.Role;
import io.kevindewit.service.authentication.model.User;
import io.kevindewit.service.authentication.repository.RoleRepository;
import io.kevindewit.service.authentication.repository.UserRepository;
import io.kevindewit.service.authentication.model.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        User user = new User();

        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.getRoles().add(roleRepository.findOneByName(ROLE_NAME.ROLE_USER).get());

        userRepository.save(user);
    }

    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
