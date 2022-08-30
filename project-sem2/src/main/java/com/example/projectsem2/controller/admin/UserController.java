package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Role;
import com.example.projectsem2.model.User;
import com.example.projectsem2.service.impl.RoleServiceImpl;
import com.example.projectsem2.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

//    @Autowired
//    GetInfo getInfo;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<User> userLists = userService.findAll();

        model.addAttribute("userLists", userLists);
        model.addAttribute("title", "User");

//        getInfo.GetAllIn4(model);

        return "admin/user/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        List<Role> roleLists = roleService.findAll();

        model.addAttribute("user", new User());
        model.addAttribute("title", "Add User");
        model.addAttribute("roleLists", roleLists);

        return "admin/user/create";
    }

    @PostMapping("/store")
    public String store(
            User user,
            RedirectAttributes ra
    ) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);

        ra.addFlashAttribute("msg", "Create Success");

        userService.save(user);

        return "redirect:/admin/user";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes ra
    ) {
        userService.deleteById(id);

        ra.addFlashAttribute("msg", "Delete Success");

        return "redirect:/admin/user";
    }
}
