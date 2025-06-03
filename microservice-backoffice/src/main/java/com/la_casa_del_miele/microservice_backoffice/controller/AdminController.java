package com.la_casa_del_miele.microservice_backoffice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.la_casa_del_miele.microservice_backoffice.dto.AdminDto;
import com.la_casa_del_miele.microservice_backoffice.dto.AdminRequestDto;
import com.la_casa_del_miele.microservice_backoffice.model.Admin;
import com.la_casa_del_miele.microservice_backoffice.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody AdminRequestDto adminRequestDto) {
        try {
            AdminDto adminDto = new AdminDto(adminRequestDto.getUsername(), adminRequestDto.getPassword());
            Admin registeredAdmin = adminService.registerNewAdmin(adminDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredAdmin);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
