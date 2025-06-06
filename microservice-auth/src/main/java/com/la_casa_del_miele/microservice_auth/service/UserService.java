package com.la_casa_del_miele.microservice_auth.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.la_casa_del_miele.microservice_auth.dto.PasswordChangeRequest;
import com.la_casa_del_miele.microservice_auth.model.Role;
import com.la_casa_del_miele.microservice_auth.model.User;

public interface UserService {
    List<User> getAllUsers();
    User saveUser(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAllUsers();
    void deleteUser(Long id);
    void changePassword(Long userId, PasswordChangeRequest dto);
    void assignRoleToUser(Long userId, Role role);
    void assignRolesToUser(Long userId, Set<Role> roles);
    List<User> findUsersByRole(String roleName);
}
