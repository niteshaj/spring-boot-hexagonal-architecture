package com.example.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class ProductEntity extends TransactionalEntity{

    private String name;

    private String description;
}
