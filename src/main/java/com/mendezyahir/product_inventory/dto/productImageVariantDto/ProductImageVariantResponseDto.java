package com.mendezyahir.product_inventory.dto.productImageVariantDto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class ProductImageVariantResponseDto {
    private Long id;
    private Long productImageId;
    private String url;
    private int width;
    private int height;
    private String resolution;
}
