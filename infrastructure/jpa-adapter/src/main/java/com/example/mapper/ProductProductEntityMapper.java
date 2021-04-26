package com.example.mapper;

import com.example.entities.ProductEntity;
import com.example.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductProductEntityMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    ProductEntity toProductEntity(Product entity);

    Product toProduct(ProductEntity entity);
}
