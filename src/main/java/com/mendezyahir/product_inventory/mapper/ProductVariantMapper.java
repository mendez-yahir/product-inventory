package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productVariantDto.ProductVariantRequestDto;
import com.mendezyahir.product_inventory.dto.productVariantDto.ProductVariantResponseDto;
import com.mendezyahir.product_inventory.entity.ProductVariant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariant toEntity(ProductVariantRequestDto requestDto);
    ProductVariantResponseDto toResponse(ProductVariant productVariant);
}
