package com.example.projectone.HTMLControllers;

import com.example.projectone.Entity.Product;
import com.example.projectone.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductControllerHtml {
    @Autowired
   private final ProductService productService;

    @GetMapping("/AllProduct")
    public String GetProductSite(Model model){
        List<Product> allProduct = productService.getAllProductInSite();
        model.addAttribute("products", allProduct);
        return "/Product";
    }
}
