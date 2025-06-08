package com.example.mapper;

import com.example.entities.ProductEntity;
import com.example.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductProductEntityMapper {


    ProductEntity toProductEntity(Product entity);

    /*@Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")*/
    Product toProduct(ProductEntity entity);
}
