package com.mendezyahir.product_inventory.dto.productDto;


import lombok.Getter;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal discountPercentage;
    private Long productBrandId;
    private Set<Long> productCategoryIds;
}
