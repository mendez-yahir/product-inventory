package com.mendezyahir.product_inventory.dto.productBrandDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductBrandResponseDto {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;
}
