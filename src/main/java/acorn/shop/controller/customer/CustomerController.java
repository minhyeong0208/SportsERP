package acorn.shop.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import acorn.shop.service.customer.CustomerService;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
}