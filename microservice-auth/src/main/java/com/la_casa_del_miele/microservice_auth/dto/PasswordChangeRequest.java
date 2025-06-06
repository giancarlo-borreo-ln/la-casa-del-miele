package com.la_casa_del_miele.microservice_auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PasswordChangeRequest {

    @NotBlank
    private String oldPassword;

    @NotBlank @Size(min = 4, max = 100)
    private String newPassword;
    
    public PasswordChangeRequest() {}

	public PasswordChangeRequest(@NotBlank String oldPassword, @NotBlank @Size(min = 4, max = 100) String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

    
}
