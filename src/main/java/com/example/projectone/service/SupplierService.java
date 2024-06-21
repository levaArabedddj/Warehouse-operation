package com.example.projectone.service;

import com.example.projectone.Entity.Supplier;
import com.example.projectone.Repository.SupplierRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}

