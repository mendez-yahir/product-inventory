package com.mendezyahir.product_inventory.dto.productCategoryDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductCategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private String sizeGuideUrl;
}
