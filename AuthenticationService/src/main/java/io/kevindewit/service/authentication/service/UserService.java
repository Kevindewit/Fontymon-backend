package io.kevindewit.service.authentication.service;

import io.kevindewit.service.authentication.repository.UserRepository;
import io.kevindewit.service.authentication.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getUserInfo(String username) {
        return userRepository.findOneByUsername(username)
                .map(user -> new UserResponse(user.getUsername(), user.getEmail()))
                .orElseThrow(() -> new EntityNotFoundException("User not found!"))
                ;
    }
}
