package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.iotstar.service.ProductService;

@Controller
public class AppController {

    @Autowired
    private ProductService productService;

    // Trang chủ: hiển thị danh sách sản phẩm
    @GetMapping({ "/", "/index" })
    public String index(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index"; // index.jsp
    }

    // Trang login
    @GetMapping("/login")
    public String login() {
        return "login"; // login.jsp
    }

    // Trang 403 (Access Denied)
    @GetMapping("/403")
    public String accessDenied() {
        return "403"; // 403.jsp
    }
}
