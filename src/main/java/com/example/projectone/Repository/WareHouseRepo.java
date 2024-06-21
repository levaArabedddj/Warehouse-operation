package com.example.projectone.Repository;

import com.example.projectone.Entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WareHouseRepo extends JpaRepository<Warehouse, Long> {
}
