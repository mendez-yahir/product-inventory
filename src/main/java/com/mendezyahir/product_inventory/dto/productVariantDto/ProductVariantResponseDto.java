package com.mendezyahir.product_inventory.dto.productVariantDto;

import java.math.BigDecimal;

import com.mendezyahir.product_inventory.entity.BaseColor;
import com.mendezyahir.product_inventory.entity.Product;
import com.mendezyahir.product_inventory.entity.ProductSize;
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

    private Product product;
    private BaseColor baseColor;
    private ProductSize productSize;
}

