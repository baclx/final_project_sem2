package com.example.projectsem2.auth.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String access_token;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.access_token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
