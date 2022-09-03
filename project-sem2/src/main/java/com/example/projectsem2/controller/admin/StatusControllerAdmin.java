package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Status;
import com.example.projectsem2.service.impl.StatusServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/status")
public class StatusControllerAdmin {
    @Autowired
    StatusServiceImplAdmin statusService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        List<Status> statusLists = statusService.findAll();

        model.addAttribute("title", "Status");
        model.addAttribute("statusLists", statusLists);

        return "admin/status/index";
    }
}
