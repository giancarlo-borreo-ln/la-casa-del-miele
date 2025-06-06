package com.la_casa_del_miele.microservice_auth.dto;

import com.la_casa_del_miele.microservice_auth.model.User;

public class UserProfileResponse {

    private Long   id;
    private String email;
    private String firstName;
    private String lastName;

    public UserProfileResponse() { }

    public UserProfileResponse(Long id,
                               String email,
                               String firstName,
                               String lastName) {
        this.id        = id;
        this.email     = email;
        this.firstName = firstName;
        this.lastName  = lastName;
    }
    
    public UserProfileResponse(User user) {
        this(user.getId(),
             user.getEmail(),
             user.getFirstName(),   
             user.getLastName());   
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
