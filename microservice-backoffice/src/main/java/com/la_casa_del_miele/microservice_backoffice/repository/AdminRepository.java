package com.la_casa_del_miele.microservice_backoffice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.la_casa_del_miele.microservice_backoffice.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByUsername(String username);
    Optional<Admin> findByUsername(String username);
}
