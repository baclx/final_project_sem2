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
import java.util.*;

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
        Long allOrderNotDone = orderService.countAllOrderStatusNotDone();

        model.addAttribute("title", "Order");
        model.addAttribute("allOrder", allOrder);
        model.addAttribute("allOrderDone", allOrderDone);
        model.addAttribute("allOrderNotDone", allOrderNotDone);

        return "admin/order/index";
    }

    @GetMapping("show/{id}")
    public String show(
            @PathVariable("id") Long id,
            Model model,
            HttpServletRequest request,
            RedirectAttributes ra
    ) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailService.findOrderDetailByOrderId(id);

        if (optionalOrderDetail.isPresent()) {
            model.addAttribute("order", optionalOrderDetail.get());
            model.addAttribute("title", "Order Details");
            return "admin/order/show";
        }

        ra.addFlashAttribute("err", "Not Found");
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/orderNotDone/page/1/")
    public String pagination(
            Model model
    ) {
        return orderNotDone(model, 1);
    }

    @GetMapping("orderNotDone/page/{pageNumber}")
    public String orderNotDone(
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

//        List<Order> orderLists = orderService.findAllByOrderByIdDesc();

        List<Order> orderLists = page.getContent();

        List<Order> arraylist = new ArrayList<>(orderLists);

        List<Order> listOrder = new ArrayList<>();

//        for (Iterator<Order> iterator = arraylist.iterator(); iterator.hasNext();) {
//            Order order = iterator.next();
//            if(order.getStatusByStatusId().getName().equals("Done")) {
//                iterator.remove();
//            } else {
//                listOrder.add(order);
//            }
//        }

        for (Order order : arraylist) {
            if (!Objects.equals(order.getStatusByStatusId().getName(), "Done")) {
                listOrder.add(order);
            }
        }
        model.addAttribute("orderLists", listOrder);
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
