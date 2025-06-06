package com.la_casa_del_miele.microservice_auth.dto;

public class JwtResponse {

    private String token;

    private String tokenType = "Bearer";

    private long   expiresAt;


    public JwtResponse() { }

    public JwtResponse(String token, String tokenType, long expiresAt) {
        this.token     = token;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
    }

    public JwtResponse(String token, long expiresAt) {
        this.token     = token;
        this.expiresAt = expiresAt;
    }


    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }
}
