package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandRequestDto;
import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandResponseDto;
import com.mendezyahir.product_inventory.entity.ProductBrand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductBrandMapper {
    ProductBrand toEntity(ProductBrandRequestDto requestDto);
    ProductBrandResponseDto toResponse(ProductBrand productBrand);
}
