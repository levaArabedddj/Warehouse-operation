package com.example.projectone.service;

import com.example.projectone.DTO.CategoryDTO;
import com.example.projectone.DTO.ProductDTO;
import com.example.projectone.Entity.Category;
import com.example.projectone.Entity.Product;
import com.example.projectone.Repository.CategoryRepo;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<CategoryDTO> getAllCategory(){
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(this::convertToCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(long id){
        Optional<Category> category = categoryRepo.findById(id);
        if(category.isPresent()){
            return convertToCategoryDTO(category.get());
        }else {
            throw new RuntimeException("not found this category");
        }
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

    private CategoryDTO convertToCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setNameCategory(category.getNameCategory());

        List<ProductDTO> productDTOS = category.getProductList().stream().map(this::convertToProductDTO).collect(Collectors.toList());
        categoryDTO.setProducts(productDTOS);
        return categoryDTO;
    }
    private ProductDTO convertToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setNameProduct(product.getNameProduct());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        return productDTO;
    }
}
