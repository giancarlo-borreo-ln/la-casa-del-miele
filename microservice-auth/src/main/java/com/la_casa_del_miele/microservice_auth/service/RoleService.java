package com.la_casa_del_miele.microservice_auth.service;

import java.util.List;

import com.la_casa_del_miele.microservice_auth.dto.RoleDto;
import com.la_casa_del_miele.microservice_auth.dto.RoleRequestDto;

public interface RoleService {
    RoleDto createRole(RoleRequestDto roleRequestDto);
    List<RoleDto> getAllRoles();
    RoleDto updateRole(Long id, RoleRequestDto roleRequestDto);
    void deleteRole(Long id);
}
