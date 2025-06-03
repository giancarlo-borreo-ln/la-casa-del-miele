package com.la_casa_del_miele.microservice_backoffice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.la_casa_del_miele.microservice_backoffice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
