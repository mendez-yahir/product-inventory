package com.mendezyahir.product_inventory.dto.productImageVariantDto;

import com.mendezyahir.product_inventory.entity.ProductImageVariant;
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
    private ProductImageVariant.ResolutionType resolution;
}
