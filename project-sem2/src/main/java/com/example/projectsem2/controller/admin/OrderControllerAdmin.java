package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.model.Status;
import com.example.projectsem2.service.impl.OrderServiceImplAdmin;
import com.example.projectsem2.service.impl.StatusServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/order")
public class OrderControllerAdmin {
    @Autowired
    OrderServiceImplAdmin orderService;

    @Autowired
    StatusServiceImplAdmin statusService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        model.addAttribute("title", "Order");

        return "admin/order/index";
    }

    @GetMapping("orderNotDone")
    public String orderNotDone(
            Model model
    ) {
        List<Order> orderLists = orderService.findAllByOrderByIdDesc();

        model.addAttribute("title", "Order Pending...");
        model.addAttribute("orderLists", orderLists);

        return "admin/order/notDone/index";
    }

    @GetMapping("orderDone")
    public String orderDone(
            Model model
    ) {
        List<Order> orderLists = orderService.findAllOrderWhereStatusDone();

        model.addAttribute("title", "Order Done");
        model.addAttribute("orderLists", orderLists);

        return "admin/order/done/index";
    }

    @GetMapping("/updateStatus/{id}")
    public String updateStatus(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<Order> optionalOrder = orderService.findById(id);

        Optional<Status> optionalStatus = statusService.findById(3L);

        return upgradeStatus(request, ra, optionalOrder, optionalStatus);
    }

    @GetMapping("/cancelStatus/{id}")
    public String cancelStatus(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<Order> optionalOrder = orderService.findById(id);

        Optional<Status> optionalStatus = statusService.findById(4L);

        return upgradeStatus(request, ra, optionalOrder, optionalStatus);
    }

    private String upgradeStatus(
            HttpServletRequest request,
            RedirectAttributes ra,
            Optional<Order> optionalOrder,
            Optional<Status> optionalStatus
    ) {

        if (optionalOrder.isPresent()) {
            optionalOrder.get().setStatusByStatusId(optionalStatus.get());
            orderService.save(optionalOrder.get());
            String referer = request.getHeader("Referer");
            return "redirect:"+ referer;
        }

        ra.addFlashAttribute("err", "ERR");
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
