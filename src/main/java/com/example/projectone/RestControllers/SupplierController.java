package com.example.projectone.RestControllers;


import com.example.projectone.DTO.SupplierDTO;
import com.example.projectone.Entity.Supplier;
import com.example.projectone.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/createSupplier")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return ResponseEntity.ok(supplierService.createSupplier(supplier));
    }

    @GetMapping("/getAllSuppliers")
    public List<SupplierDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/getSuppliers/{id}")
    public SupplierDTO getSupplierById(@PathVariable Long id) throws Exception {
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/updateSupplier")
    public ResponseEntity<Supplier> updateSupplier(@RequestParam Long id, @RequestBody Supplier supplierDetails) {
        return ResponseEntity.ok(supplierService.updateSupplier(id, supplierDetails));
    }

    @DeleteMapping("/deleteSupplier")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}

