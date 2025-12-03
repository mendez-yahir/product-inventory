package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryRequestDto;
import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryResponseDto;
import com.mendezyahir.product_inventory.entity.ProductCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    ProductCategory toEntity(ProductCategoryRequestDto requestDto);
    ProductCategoryResponseDto toResponse(ProductCategory productCategory);
}
