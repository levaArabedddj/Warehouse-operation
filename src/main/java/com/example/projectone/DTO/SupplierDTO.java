package com.example.projectone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private long id;
    private String nameProduct;
    private String numberPhone;

    private List<ProductDTO> products;
}


