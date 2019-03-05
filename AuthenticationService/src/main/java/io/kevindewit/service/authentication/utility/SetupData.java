package io.kevindewit.service.authentication.utility;

import io.kevindewit.service.authentication.model.Participant;
import io.kevindewit.service.authentication.model.Role;
import io.kevindewit.service.authentication.repository.ParticipantRepository;
import io.kevindewit.service.authentication.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class SetupData {

    private final ParticipantRepository participantRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SetupData(ParticipantRepository participantRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.participantRepository = participantRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initParticipants();
    }

    private void initParticipants() {
        final Role roleAdmin = roleRepository.findByRole("ROLE_ADMIN");
        final Role roleTeacher = roleRepository.findByRole("ROLE_TEACHER");
        final Role roleStudent = roleRepository.findByRole("ROLE_STUDENT");

        final Participant participantAdmin =
                new Participant(
                        "Admin",
                        bCryptPasswordEncoder.encode("Admin"),
                        "Admin@Example.com",
                        new HashSet<>(Arrays.asList(roleAdmin))
                );
        participantRepository.save(participantAdmin);

        final Participant participantTeacher =
        new Participant(
                "Teacher",
                bCryptPasswordEncoder.encode("Teacher"),
                "Teacher@Example.com",
                new HashSet<>(Arrays.asList(roleTeacher))
        );
        participantRepository.save(participantTeacher);

        final Participant participantStudent =
                new Participant(
                        "Student",
                        bCryptPasswordEncoder.encode("Student"),
                        "Student@Example.com",
                        new HashSet<>(Arrays.asList(roleStudent))
                );
        participantRepository.save(participantStudent);
    }

    private void initRoles() {
        final Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleAdmin);

        final Role roleTeacher = new Role("ROLE_TEACHER");
        roleRepository.save(roleTeacher);

        final Role roleStudent = new Role("ROLE_STUDENT");
        roleRepository.save(roleStudent);
    }

}
