package com.edutecno.backend.config;

import com.edutecno.backend.model.AuthorityEnum;
import com.edutecno.backend.model.Role;
import com.edutecno.backend.model.User;
import com.edutecno.backend.repository.RoleRepository;
import com.edutecno.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role role_user = new Role();
            role_user.setName(AuthorityEnum.valueOf(AuthorityEnum.ROLE_CLIENT.name()));
            Role role_admin = new Role();
            role_admin.setName(AuthorityEnum.valueOf(AuthorityEnum.ROLE_ADMIN.name()));
            roleRepository.save(role_user);
            roleRepository.save(role_admin);
        }

        if (userRepository.count() == 0) {
            Optional<Role> optionalRoleUser = roleRepository.findByName(AuthorityEnum.ROLE_ADMIN);
            List<Role> roles = new ArrayList<>();
            optionalRoleUser.ifPresent(roles::add);
            User user = new User();
            user.setName("admin");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setEmail("admin@admin.com");
            user.setEnabled(true);
            user.setRoles(roles);
            userRepository.save(user);
        }

    }
}
