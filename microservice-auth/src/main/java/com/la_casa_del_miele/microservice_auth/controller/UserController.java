package com.la_casa_del_miele.microservice_auth.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.la_casa_del_miele.microservice_auth.dto.PasswordChangeRequest;
import com.la_casa_del_miele.microservice_auth.dto.UserProfileResponse;
import com.la_casa_del_miele.microservice_auth.dto.UserUpdateRequest;
import com.la_casa_del_miele.microservice_auth.model.Role;
import com.la_casa_del_miele.microservice_auth.model.User;
import com.la_casa_del_miele.microservice_auth.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<List<UserProfileResponse>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserProfileResponse> response = users.stream()
                .map(UserProfileResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        return ResponseEntity.ok(new UserProfileResponse(user));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPERADMIN', 'ADMIN')")
    public ResponseEntity<UserProfileResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UserUpdateRequest updateRequest) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        user.setFirstName(updateRequest.getFirstName());
        user.setLastName(updateRequest.getLastName());
        User updatedUser = userService.saveUser(user);
        return ResponseEntity.ok(new UserProfileResponse(updatedUser));
    }

    @PatchMapping("/{id}/change-password")
    @PreAuthorize("#id == principal.id or hasRole('SUPERADMIN')")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody PasswordChangeRequest passwordChangeRequest) {
        userService.changePassword(id, passwordChangeRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/roles")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Void> assignRoleToUser(
            @PathVariable Long id,
            @RequestBody Role role) {
        userService.assignRoleToUser(id, role);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/roles/batch")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<Void> assignRolesToUser(
            @PathVariable Long id,
            @RequestBody Set<Role> roles) {
        userService.assignRolesToUser(id, roles);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/by-role/{roleName}")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<List<UserProfileResponse>> getUsersByRole(@PathVariable String roleName) {
        List<User> users = userService.findUsersByRole(roleName);
        List<UserProfileResponse> response = users.stream()
                .map(UserProfileResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
