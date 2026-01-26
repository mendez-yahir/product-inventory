package com.mendezyahir.product_inventory.dto.productImageDto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class ProductImageResponseDto {
    private Long id;
    private String altText;
    private Boolean isPrimary;
    private String thumbnailUrl;
}
