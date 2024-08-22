package acorn.shop.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import acorn.shop.service.product.ProductService;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
}
