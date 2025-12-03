package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageRequestDto;
import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageResponseDto;
import com.mendezyahir.product_inventory.entity.ProductImage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    ProductImage toEntity(ProductImageRequestDto requestDto);
    ProductImageResponseDto toResponse(ProductImage productImage);
}
