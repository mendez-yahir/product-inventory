package com.mendezyahir.product_inventory.dto.productDto;

import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandResponseDto;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Long productBrandId;
    private Set<Long> productCategoryIds;
}
