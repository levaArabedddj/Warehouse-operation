package com.example.projectone.service;

import com.example.projectone.Entity.Category;
import com.example.projectone.Repository.CategoryRepo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo category) {
        this.categoryRepo = category;
    }
    public Category createCategory(Category categories){
        categoryRepo.save(categories);
        return categories;
    }

    @SneakyThrows
    public Category updateCategory(Long id, Category category){
        Optional<Category> optional = categoryRepo.findById(id);
        if(optional.isPresent()){
            Category categoryExist = optional.get();
            categoryExist.setNameCategory(category.getNameCategory());
            categoryExist.setProductList(category.getProductList());
            return categoryRepo.save(categoryExist);
        } else {
            throw new Exception("category by id :"+ id +" not found");
        }

    }

    public void deleteCategory(long id){
        categoryRepo.deleteById(id);
    }
}
