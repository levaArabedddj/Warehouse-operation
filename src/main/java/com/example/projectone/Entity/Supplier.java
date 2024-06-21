package com.example.projectone.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nameProduct;
    private String numberPhone;
    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Product> productList;
}
