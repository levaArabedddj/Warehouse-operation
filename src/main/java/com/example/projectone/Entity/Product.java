package com.example.projectone.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProduct;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private int price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Order> orders;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Warehouse>warehouseSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
