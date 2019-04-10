package io.kevindewit.service.authentication.utility;

import io.kevindewit.service.authentication.model.ROLE_NAME;
import io.kevindewit.service.authentication.model.Role;
import io.kevindewit.service.authentication.model.User;
import io.kevindewit.service.authentication.repository.RoleRepository;
import io.kevindewit.service.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<Role> roles = new ArrayList<Role>() {{
           add(new Role(ROLE_NAME.ROLE_USER));
           add(new Role(ROLE_NAME.ROLE_DEVELOPER));
           add(new Role(ROLE_NAME.ROLE_MODERATOR));
           add(new Role(ROLE_NAME.ROLE_ADMIN));
           add(new Role(ROLE_NAME.ROLE_SYSTEM));
        }};

        for (Role role : roles)
            roleRepository.save(role);

        List<User> users = new ArrayList<User>() {{
            add(new User("user@fontymon.io", "user", "DemoPassword"));
            add(new User("developer@fontymon.io", "developer", "DemoPassword"));
            add(new User("moderator@fontymon.io", "moderator", "DemoPassword"));
            add(new User("admin@fontymon.io", "admin", "DemoPassword"));
            add(new User("system@fontymon.io", "system", "DemoPassword"));
        }};

        for (User user : users) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(roles.get(users.indexOf(user)));
            userRepository.save(user);
        }
    }

}
