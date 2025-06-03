package com.la_casa_del_miele.microservice_backoffice.config;

import com.la_casa_del_miele.microservice_backoffice.model.Admin;
import com.la_casa_del_miele.microservice_backoffice.model.Role;
import com.la_casa_del_miele.microservice_backoffice.repository.AdminRepository;
import com.la_casa_del_miele.microservice_backoffice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdminRepository adminRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.count() == 0) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role newRole = new Role("ROLE_ADMIN");
                        return roleRepository.save(newRole);
                    });

            Admin superAdmin = new Admin();
            superAdmin.setUsername("superadmin");
            superAdmin.setPassword(passwordEncoder.encode("1234"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            superAdmin.setRoles(roles);

            adminRepository.save(superAdmin);
        }
    }
}
