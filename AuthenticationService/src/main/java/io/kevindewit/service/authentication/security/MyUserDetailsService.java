package io.kevindewit.service.authentication.security;

import io.kevindewit.service.authentication.model.Participant;
import io.kevindewit.service.authentication.model.Role;
import io.kevindewit.service.authentication.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public MyUserDetailsService(ParticipantRepository participantRepository) {
        super();
        this.participantRepository = participantRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Participant participant = participantRepository.findByUsername(username);

        if (participant == null)
            throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : participant.getRoles())
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));

        return new org.springframework.security.core.userdetails.User(participant.getUsername(), participant.getPassword(), grantedAuthorities);
    }
}
