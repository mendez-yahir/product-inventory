package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeRequestDto;
import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductSizeMapper {
    ProductSizeMapper toEntity(ProductSizeRequestDto requestDto);
    ProductSizeResponseDto toResponse(ProductSizeMapper productSize);
}
