package com.mendezyahir.product_inventory.dto.productVariantDto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductVariantResponseDto {
    private Long id;
    private String sku;
    private BigDecimal price;
    private BigDecimal weightKg;
    private String productColorUrl;
    private int stock;
    private Long productId;
    private Long baseColorId;
    private Long productSizeId;
}

