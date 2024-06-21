package com.example.projectone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    private Long id;
    private String adress;
    private BigDecimal square;
    private Set<ProductDTO> products;
    private List<EmployeeDTO> employeeList;
}


