package com.la_casa_del_miele.microservice_auth.dto;

public class RoleRequestDto {
    private String name;

    public RoleRequestDto() {
    }

    public RoleRequestDto(String name) {
        this.name = name;
    }

    // Getter e Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
