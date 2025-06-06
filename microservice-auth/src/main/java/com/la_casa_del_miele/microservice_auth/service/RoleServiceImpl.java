package com.la_casa_del_miele.microservice_auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.la_casa_del_miele.microservice_auth.dto.RoleDto;
import com.la_casa_del_miele.microservice_auth.dto.RoleRequestDto;
import com.la_casa_del_miele.microservice_auth.model.Role;
import com.la_casa_del_miele.microservice_auth.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto createRole(RoleRequestDto roleRequestDto) {
        Role role = new Role();
        role.setName(roleRequestDto.getName());
        Role savedRole = roleRepository.save(role);
        return convertToDto(savedRole);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto updateRole(Long id, RoleRequestDto roleRequestDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
        role.setName(roleRequestDto.getName());
        Role updatedRole = roleRepository.save(role);
        return convertToDto(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    private RoleDto convertToDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
}
