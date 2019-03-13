package io.kevindewit.service.authentication.utility;

import io.kevindewit.service.authentication.model.ROLE_NAME;
import io.kevindewit.service.authentication.model.Role;
import io.kevindewit.service.authentication.model.User;
import io.kevindewit.service.authentication.repository.RoleRepository;
import io.kevindewit.service.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleRepository.save(new Role(ROLE_NAME.ROLE_USER));
        roleRepository.save(new Role(ROLE_NAME.ROLE_DEVELOPER));
        roleRepository.save(new Role(ROLE_NAME.ROLE_ADMIN));

        userRepository.save(new User("Kevin", "de Wit", "Quevin", "Kevindewit@me.com", "koehandel123"));
    }
}
