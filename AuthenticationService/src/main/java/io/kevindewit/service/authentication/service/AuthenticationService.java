package io.kevindewit.service.authentication.service;

import io.kevindewit.service.authentication.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final ParticipantRepository repository;

    @Autowired
    public AuthenticationService(ParticipantRepository repository) {
        this.repository = repository;    }

}
