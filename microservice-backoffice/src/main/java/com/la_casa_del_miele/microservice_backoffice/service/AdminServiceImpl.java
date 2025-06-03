package com.la_casa_del_miele.microservice_backoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.la_casa_del_miele.microservice_backoffice.dto.AdminDto;
import com.la_casa_del_miele.microservice_backoffice.model.Admin;
import com.la_casa_del_miele.microservice_backoffice.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin registerNewAdmin(AdminDto adminDto) throws Exception {
        if (adminRepository.existsByUsername(adminDto.getUsername())) {
            throw new Exception("Username already exists: " + adminDto.getUsername());
        }

        Admin admin = new Admin();
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));

        return adminRepository.save(admin);
    }
}
