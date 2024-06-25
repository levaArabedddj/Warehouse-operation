package com.example.projectone.RestControllers;

import com.example.projectone.DTO.CategoryDTO;
import com.example.projectone.Entity.Category;
import com.example.projectone.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryControllers {
    private final CategoryService categoryService;

    public CategoryControllers(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    private Category createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return category;
    }

    @GetMapping("/getAllCategories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategory();
    }
    @GetMapping("/getCategoryById")
    public CategoryDTO getCategoryById(@RequestParam long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/updateCategory")
    private Category updateCategory(@RequestParam long id, @RequestBody Category category){
        categoryService.updateCategory(id,category);
        return category;
    }
    @DeleteMapping("/deleteCategory")
    private String deleteCategory(@RequestParam long id){
        categoryService.deleteCategory(id);
        return "Category  successful delete";
    }
}
