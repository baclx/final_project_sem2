package com.example.projectsem2.controller.admin;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.model.OrderDetail;
import com.example.projectsem2.model.Status;
import com.example.projectsem2.service.impl.OrderDetailServiceImplAdmin;
import com.example.projectsem2.service.impl.OrderServiceImplAdmin;
import com.example.projectsem2.service.impl.StatusServiceImplAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/order")
public class OrderControllerAdmin {
    @Autowired
    OrderServiceImplAdmin orderService;

    @Autowired
    StatusServiceImplAdmin statusService;

    @Autowired
    OrderDetailServiceImplAdmin orderDetailService;

    @GetMapping("")
    public String index(
            Model model
    ) {
        Long allOrder = orderService.countAllOrder();
        Long allOrderDone = orderService.countAllOrderStatusDone();
        Long allOrderPending = orderService.countAllOrderStatusPending();
        Long allOrderCancel = orderService.countAllOrderStatusCancelled();

        model.addAttribute("title", "Order");
        model.addAttribute("allOrder", allOrder);
        model.addAttribute("allOrderDone", allOrderDone);
        model.addAttribute("allOrderPending", allOrderPending);
        model.addAttribute("allOrderCancel", allOrderCancel);

        return "admin/order/index";
    }

    @GetMapping("show/{id}")
    public String show(
            @PathVariable("id") Long id,
            Model model
    ) {
        List<OrderDetail> orderDetailLists = orderDetailService.findOrderDetailByOrderId(id);

        model.addAttribute("orders", orderDetailLists);
        model.addAttribute("title", "Order Details");
        return "admin/order/show";
    }

    @GetMapping("/page/1/")
    public String pagination(
            Model model
    ) {
        return orderPagination(model, 1);
    }

    @GetMapping("/page/{pageNumber}")
    public String orderPagination(
            Model model,
            @PathVariable("pageNumber") int currentPage
    ) {
        // pagination
        Page<Order> page = orderService.pagination(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        List<Order> orderLists = new ArrayList<>(page.getContent());

//        List<Order> list = orderLists.stream()
//                .sorted(Comparator.comparing(Order::getId).reversed())
//                .collect(Collectors.toList());

//        Collections.reverse(orderLists);

        model.addAttribute("orderLists", orderLists);
        model.addAttribute("title", "Order Pending...");

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

        Optional<Status> optionalStatus = statusService.findByName("Done");

        return upgradeStatus(request, ra, optionalOrder, optionalStatus);
    }

    @GetMapping("/cancelStatus/{id}")
    public String cancelStatus(
            @PathVariable("id") Long id,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<Order> optionalOrder = orderService.findById(id);

        Optional<Status> optionalStatus = statusService.findByName("Cancelled");

        if (optionalOrder.isPresent() && !optionalOrder.get().getStatusByStatusId().getName().equals("Done")) {
            return upgradeStatus(request, ra, optionalOrder, optionalStatus);
        }

        ra.addFlashAttribute("err", "Not Update Status Done");
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    private String upgradeStatus(
            HttpServletRequest request,
            RedirectAttributes ra,
            Optional<Order> optionalOrder,
            Optional<Status> optionalStatus
    ) {

        if (optionalOrder.isPresent() && optionalStatus.isPresent()) {
            if (!optionalOrder.get().getStatusByStatusId().equals(optionalStatus.get())) {
                ra.addFlashAttribute("msg", "Update Success");
                optionalOrder.get().setStatusByStatusId(optionalStatus.get());
                orderService.save(optionalOrder.get());
            }
            String referer = request.getHeader("Referer");
            return "redirect:"+ referer;
        }

        ra.addFlashAttribute("err", "ERR");
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
