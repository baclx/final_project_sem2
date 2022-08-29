package com.example.projectsem2.auth.payload.request;

import com.example.projectsem2.model.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    Set<String> roles;
}
