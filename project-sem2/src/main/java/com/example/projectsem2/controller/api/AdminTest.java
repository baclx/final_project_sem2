package com.example.projectsem2.controller.api;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.service.impl.OrderServiceImplAdmin;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AdminTest {
    private final OrderServiceImplAdmin orderService;

    public AdminTest(OrderServiceImplAdmin orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<Page<Order>> orderPagination(
            @PathVariable("pageNumber") int currentPage
    ) {
        Page<Order> orderPage = orderService.listPage(currentPage, 5);

        return new ResponseEntity<>(orderPage, HttpStatus.OK);
    }
}
