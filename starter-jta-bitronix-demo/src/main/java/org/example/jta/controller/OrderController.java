package org.example.jta.controller;

import org.example.jta.entity.TOrder;
import org.example.jta.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuanct
 * @since 1.2
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/insert")
    public String insertOrder(@RequestBody TOrder order) {
        orderService.insertOrder(order);
        return "success";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam String id) {
        orderService.deleteOrder(id);
        return "success";
    }

    @PostMapping("/update")
    public String updateOrder(@RequestBody TOrder order) {
        orderService.updateOrder(order);
        return "success";
    }
}
