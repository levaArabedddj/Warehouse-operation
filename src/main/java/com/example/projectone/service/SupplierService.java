package com.example.projectone.service;

import com.example.projectone.DTO.CategoryDTO;
import com.example.projectone.DTO.ProductDTO;
import com.example.projectone.DTO.SupplierDTO;
import com.example.projectone.Entity.Supplier;
import com.example.projectone.Repository.SupplierRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepository;

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @SneakyThrows
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier existingSupplier = optionalSupplier.get();
            existingSupplier.setNameProduct(supplierDetails.getNameProduct());
            existingSupplier.setNumberPhone(supplierDetails.getNumberPhone());
            existingSupplier.setProductList(supplierDetails.getProductList());
            return supplierRepository.save(existingSupplier);
        } else {
            throw new Exception("Supplier with id " + id + " not found");
        }
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    // Получение всех поставщиков
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(this::convertToSupplierDTO).collect(Collectors.toList());
    }

    // Получение одного поставщика по ID
    public SupplierDTO getSupplierById(Long id) throws Exception {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new Exception("Supplier not found with id " + id));
        return convertToSupplierDTO(supplier);
    }

    private SupplierDTO convertToSupplierDTO(Supplier supplier){
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(supplier.getId());
        supplierDTO.setNameProduct(supplier.getNameProduct());
        supplierDTO.setNumberPhone(supplier.getNumberPhone());

        List<ProductDTO> productDTO = supplier.getProductList().stream()
                .map(product -> {
                    ProductDTO productDTOs = new ProductDTO();
                    productDTOs.setId(product.getId());
                    productDTOs.setNameProduct(product.getNameProduct());
                    productDTOs.setPrice(product.getPrice());
                    productDTOs.setQuantity(product.getQuantity());
                    if(product.getCategory() != null){
                        CategoryDTO categoryDTO = new CategoryDTO();
                        categoryDTO.setId(product.getCategory().getId());
                        categoryDTO.setNameCategory(product.getCategory().getNameCategory());
                        productDTOs.setCategory(categoryDTO);
                    }
                    return productDTOs;
                }).collect(Collectors.toList());
        supplierDTO.setProducts(productDTO);
        return supplierDTO;
    }
}

