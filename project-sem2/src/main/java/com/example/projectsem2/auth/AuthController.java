package com.example.projectsem2.auth;

import com.example.projectsem2.auth.common.Const;
import com.example.projectsem2.auth.jwt.JwtUtils;
import com.example.projectsem2.model.Role;
import com.example.projectsem2.model.User;
import com.example.projectsem2.service.impl.RoleServiceImplAdmin;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
public class AuthController {
    @Autowired
    UserServiceImplAdmin usersService;
    @Autowired
    RoleServiceImplAdmin roleService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

//    @PostMapping("/process_login")
//    public void processLogin(LoginRequest loginRequest) {
//        if (usersService.findByEmail(loginRequest.getEmail()).isPresent()) {
//
//        }
//    }

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

    @GetMapping("/data")
    public ResponseEntity<String> getToken(
            @RequestHeader(value="Authorization")  String authorizationHeader
    ){
        String jwt = authorizationHeader.substring(7);
        return ResponseEntity.ok(jwt);
    }
}
