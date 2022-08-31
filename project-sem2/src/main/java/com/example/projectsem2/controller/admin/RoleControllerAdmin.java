package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Category;
import com.example.projectsem2.model.Role;
import com.example.projectsem2.service.impl.RoleServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/role")
public class RoleControllerAdmin {
    @Autowired
    RoleServiceImplAdmin roleService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Role> roleLists = roleService.findAll();

        model.addAttribute("roleLists", roleLists);
        model.addAttribute("title", "Role");

        return "admin/role/index";
    }

    @GetMapping("/add")
    public String add(
            Model model
    ) {
        model.addAttribute("role", new Role());
        model.addAttribute("title", "Create Role");

        return "admin/role/create";
    }

    @PostMapping("/store")
    public String store(
            Role role,
            RedirectAttributes ra
    ) {
        roleService.save(role);
        ra.addFlashAttribute("msg","Create Success");

        return "redirect:/admin/role";
    }

    @GetMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") Long id,
            RedirectAttributes ra
    ) {
        Optional<Role> role = roleService.findById(id);

        if (role.isPresent()) {
            roleService.deleteById(id);
            ra.addFlashAttribute(   "msg", "Delete Success");
            return "redirect:/admin/role";
        }

        ra.addFlashAttribute("err", "ID not found");
        return "redirect:/admin/role";
    }
}