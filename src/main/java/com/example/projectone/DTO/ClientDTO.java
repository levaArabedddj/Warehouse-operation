package com.example.projectone.DTO;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String surName;
    private String phoneNumber;
    private List<OrderDTO> orders;
}



