package com.example.projectone.service;

import com.example.projectone.Entity.Employee;
import com.example.projectone.Repository.EmployeeRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @SneakyThrows
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setGmail(employeeDetails.getGmail());
            existingEmployee.setWarehouse(employeeDetails.getWarehouse());
            existingEmployee.setRole(employeeDetails.getRole());
            return employeeRepository.save(existingEmployee);
        } else {
            throw new Exception("Employee with id " + id + " not found");
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}

