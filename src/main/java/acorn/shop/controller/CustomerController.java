package acorn.shop.controller;

import acorn.shop.dto.CustomerDto;
import acorn.shop.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute CustomerDto customerDto) {
        customerService.registerCustomer(customerDto);
        return "redirect:/auth/login";
    }

    @GetMapping("/main")
    public String showMainPage(HttpSession session, Model model) {
        String customerId = (String) session.getAttribute("customerId");
        if (customerId != null) {
            model.addAttribute("customerId", customerId);
            return "main";
        } else {
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/check-id")
    @ResponseBody
    public boolean checkIdAvailability(@RequestParam String value) {
        return !customerService.isIdDuplicate(value);
    }
}
