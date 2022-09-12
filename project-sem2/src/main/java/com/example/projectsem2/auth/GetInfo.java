package com.example.projectsem2.auth;

import com.example.projectsem2.auth.service.UserDetailsImpl;
import com.example.projectsem2.model.Role;
import com.example.projectsem2.model.User;
import com.example.projectsem2.service.UserService;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Optional;

@Component
public class GetInfo {
    @Autowired
    UserServiceImplAdmin userService;

    public String getEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetailsImpl) principal).getEmail();

        } else {
            email = principal.toString();
        }
        Optional<User> opUsert = userService.findByEmail(email);
        User u = opUsert.orElseGet(User::new);
        return u.getEmail();
    }

    public void GetAllIn4(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userService.findByEmail(currentPrincipalName);

        // functional style expression
        User u = user.orElseGet(User::new);

        model.addAttribute("userID", u.getId());
        model.addAttribute("username", u.getUsername());
        model.addAttribute("email", u.getEmail());

        for (Role role : u.getRoles()) {
            model.addAttribute("role", role.getName());
        }
    }

}
