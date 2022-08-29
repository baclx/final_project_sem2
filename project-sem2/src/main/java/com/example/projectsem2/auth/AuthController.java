package com.example.projectsem2.auth;

import com.example.projectsem2.auth.common.Const;
import com.example.projectsem2.auth.jwt.JwtUtils;
import com.example.projectsem2.auth.payload.request.LoginRequest;
import com.example.projectsem2.auth.payload.response.JwtResponse;
import com.example.projectsem2.auth.service.UserDetailsImpl;
import com.example.projectsem2.model.Role;
import com.example.projectsem2.model.User;
import com.example.projectsem2.service.impl.RoleServiceImpl;
import com.example.projectsem2.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class AuthController {
    @Autowired
    UserServiceImpl usersService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/process_login")
    public void authenticateUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken obj =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(obj);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String access_token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        ResponseEntity<JwtResponse> response = ResponseEntity.ok(new JwtResponse(
                access_token,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        ));

        System.out.println(response);
    }

    @GetMapping("/register")
    public String register(
            Model model
    ) {
        model.addAttribute("user", new User());

        return "auth/register";
    }

    @PostMapping("/process_register")
    public String processRegister(
            User user,
            RedirectAttributes ra
    ) {
        if (usersService.existsByEmail(user.getEmail())) {
            ra.addFlashAttribute("notification", "Email da ton tai");
            return "redirect:/register";
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPass = encoder.encode(user.getPassword());
            user.setPassword(encodedPass);

            Set<Role> roles = new HashSet<>();
            Role role = roleService.findByName(Const.ROLE_USER).get();

            roles.add(role);
            user.setRoles(roles);

            usersService.save(user);
        }

        return "redirect:/login";
    }

    @GetMapping("/test")
    public String test() {
        return "admin/test";
    }
}
