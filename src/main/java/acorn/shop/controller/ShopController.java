package acorn.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import acorn.shop.dto.product.ProductDTO;
import acorn.shop.service.product.ProductService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Arrays;

@Controller
public class ShopController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        // 네비게이션 카테고리 목록
        List<String> categories = Arrays.asList("전체", "의류", "볼/머플러", "기타 잡화");
        model.addAttribute("categories", categories);

        // 상품 목록 (데이터베이스에서 가져옴)
        List<ProductDTO> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "shopping";
    }
}
