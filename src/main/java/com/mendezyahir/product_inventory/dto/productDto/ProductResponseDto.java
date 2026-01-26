package com.mendezyahir.product_inventory.dto.productDto;


import com.mendezyahir.product_inventory.entity.ProductBrand;
import com.mendezyahir.product_inventory.entity.ProductCategory;
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
    private ProductBrand productBrand;
    private Set<ProductCategory> productCategories;
}
