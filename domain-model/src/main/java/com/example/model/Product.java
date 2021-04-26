package com.example.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class Product {
    private Integer id;

    @NotBlank
    private String name;

    private String description;
}
