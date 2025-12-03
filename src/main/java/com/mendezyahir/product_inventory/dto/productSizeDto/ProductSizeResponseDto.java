package com.mendezyahir.product_inventory.dto.productSizeDto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class ProductSizeResponseDto {
    private Long id;
    private String label;
    private String region;
    private String numericEquivalent;
}
