package com.la_casa_del_miele.microservice_auth.config;

import com.la_casa_del_miele.microservice_auth.model.Role;
import com.la_casa_del_miele.microservice_auth.model.User;
import com.la_casa_del_miele.microservice_auth.repository.RoleRepository;
import com.la_casa_del_miele.microservice_auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {

        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        Role roleSuperAdmin = roleRepository.findByName("ROLE_SUPERADMIN")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_SUPERADMIN")));

        
        if (userRepository.findByEmail("superadmin@admin.com").isEmpty()) {
            User superAdmin = new User();
            superAdmin.setEmail("superadmin@admin.com");
            superAdmin.setFirstName("Super");
            superAdmin.setLastName("Admin");
            superAdmin.setPassword(passwordEncoder.encode("1234"));
            superAdmin.setRoles(Set.of(roleSuperAdmin));
            userRepository.save(superAdmin);
        }

        List<User> existingUsers = userRepository.findAll();
        if (existingUsers.size() < 5) {
            for (int i = 1; i <= 5; i++) {
                String email = "user" + i + "@example.com";
                if (userRepository.findByEmail(email).isEmpty()) {
                    User user = new User();
                    user.setEmail(email);
                    user.setFirstName("User" + i);
                    user.setLastName("Test");
                    user.setPassword(passwordEncoder.encode("password" + i));
                    user.setRoles(Set.of(roleUser));
                    userRepository.save(user);
                }
            }
        }
    }
}
