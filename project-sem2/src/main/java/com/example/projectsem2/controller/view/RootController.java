package com.example.projectsem2.controller.view;

import com.example.projectsem2.model.User;
import com.example.projectsem2.service.impl.UserServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class RootController {
    @Autowired
    UserServiceImplAdmin userService;

    public Long getcurrentUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();

        } else {
            username = principal.toString();
        }
        Optional<User> opUsert = userService.findByUsername(username);
        User u;
        if(opUsert.isPresent()) {
            u = opUsert.get();
        } else {
            u = new User();
        }
        Long currentUserId = u.getId();
        return currentUserId;
    }

    @GetMapping("/service")
    public String blogSingle(Model model) {
        Long id = getcurrentUserId();
        model.addAttribute("currentUserId",id);
        User currentUser = userService.getUserById(id).getBody();
        model.addAttribute("currentUser",currentUser);
        return "service";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        Long id = getcurrentUserId();
        model.addAttribute("currentUserId",id);
        User currentUser = userService.getUserById(id).getBody();
        model.addAttribute("currentUser",currentUser);
        return "contact";
    }
}
