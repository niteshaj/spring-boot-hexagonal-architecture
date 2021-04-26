package com.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class ProductEntity extends TransactionalEntity{

    private String name;

    private String description;
}
