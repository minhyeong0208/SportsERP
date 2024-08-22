package acorn.shop.controller;

import acorn.shop.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, HttpSession session, Model model) {
        if (customerService.validateLogin(id, password)) {
            session.setAttribute("customerId", id); // 세션에 고객 ID 저장
            return "redirect:/main"; // 로그인 성공 시 메인 페이지로 리다이렉션
        } else {
            model.addAttribute("error", "Invalid credentials"); // 로그인 실패 시 오류 메시지 추가
            return "login"; // 로그인 페이지로 다시 이동
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/auth/login"; // 로그아웃 후 로그인 페이지로 리다이렉션
    }
}
