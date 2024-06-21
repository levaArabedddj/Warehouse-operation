package com.example.projectone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String nameProduct;
    private CategoryDTO category;
    private int price;
    private int quantity;
    private SupplierDTO supplier;
    private Set<OrderDTO> orders;
    private Set<WarehouseDTO> warehouseSet;
}


