package vn.iotstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iotstar.entity.Product;
import vn.iotstar.service.ProductService;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. Form thêm sản phẩm (ROLE_ADMIN)
    @GetMapping("/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "new_product"; // new_product.jsp
    }

    // 2. Lưu sản phẩm mới
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
            RedirectAttributes ra) {
        productService.save(product);
        ra.addFlashAttribute("message", "Lưu sản phẩm thành công!");
        return "redirect:/index";
    }

    // 3. Form sửa sản phẩm (ROLE_ADMIN)
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            model.addAttribute("product", optionalProduct.get());
            return "edit_product"; // edit_product.jsp
        } else {
            return "redirect:/index";
        }
    }

    // 4. Xóa sản phẩm (ROLE_ADMIN)
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            productService.deleteById(id);
            ra.addFlashAttribute("message", "Xóa sản phẩm thành công!");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Không thể xóa sản phẩm này.");
        }
        return "redirect:/index";
    }
}
