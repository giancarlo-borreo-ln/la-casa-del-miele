package com.la_casa_del_miele.microservice_auth.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.la_casa_del_miele.microservice_auth.dto.PasswordChangeRequest;
import com.la_casa_del_miele.microservice_auth.model.Role;
import com.la_casa_del_miele.microservice_auth.model.User;
import com.la_casa_del_miele.microservice_auth.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("E-mail già registrata");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void changePassword(Long userId, PasswordChangeRequest dto) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("La vecchia password non è corretta");
        }

        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void assignRoleToUser(Long userId, Role role) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void assignRolesToUser(Long userId, Set<Role> roles) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        user.getRoles().addAll(roles);
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersByRole(String roleName) {
        return userRepository.findAll().stream()
                             .filter(user -> user.getRoles().stream()
                                                 .anyMatch(role -> role.getName().equals(roleName)))
                             .toList();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
