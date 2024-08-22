package acorn.shop.controller.order;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import acorn.shop.service.order.OrderService;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
}
