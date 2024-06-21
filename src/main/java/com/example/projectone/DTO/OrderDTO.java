package com.example.projectone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private LocalDate dateCreatedOrders;
    private String orderStatus;
    private Set<ProductDTO> products;
    private ClientDTO client;
    private int quantityProduct;
    private BigDecimal price;
}


