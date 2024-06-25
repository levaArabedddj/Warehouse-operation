package com.example.projectone.RestControllers;

import com.example.projectone.DTO.ProductDTO;
import com.example.projectone.Entity.Product;
import com.example.projectone.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductControllers {
    private final ProductService productService;

    public ProductControllers(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    private Product createProduct(@RequestBody Product product){
        productService.createProduct(product);
        return product;
    }

    @GetMapping("/getAll")
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getByIdProduct")
    public ProductDTO getProductId(@RequestParam long id){
        return productService.getProductById(id);
    }

    @PutMapping("/updateProduct")
    private Product updateProduct(@RequestParam long id, @RequestBody Product product) throws Exception {
        productService.update(id,product);
        return product;
    }
    @DeleteMapping("/deleteProduct")
    private String deleteProduct(@RequestParam long id){
        productService.deleteProduct(id);
        return "Product  successful delete";
    }

}
