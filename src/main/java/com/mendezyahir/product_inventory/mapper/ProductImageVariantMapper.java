package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantRequestDto;
import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantResponseDto;
import com.mendezyahir.product_inventory.entity.ProductImageVariant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductImageVariantMapper {
    ProductImageVariant toEntity(ProductImageVariantRequestDto requestDto);
    ProductImageVariantResponseDto toResponse(ProductImageVariant productImageVariant);
}
