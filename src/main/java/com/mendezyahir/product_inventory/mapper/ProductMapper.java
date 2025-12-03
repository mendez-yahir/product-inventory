package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productDto.ProductRequestDto;
import com.mendezyahir.product_inventory.dto.productDto.ProductResponseDto;
import com.mendezyahir.product_inventory.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDto requestDto);
    ProductResponseDto toResponse(Product product);
}
