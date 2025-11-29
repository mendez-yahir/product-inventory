package com.mendezyahir.product_inventory.dto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private ProductBrandResponseDto productBrand;
    private Set<ProductCategoryResponseDto> productCategories;
}
