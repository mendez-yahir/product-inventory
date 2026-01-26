package com.mendezyahir.product_inventory.dto.productVariantDto;





import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariantRequestDto {
    @NotBlank(message = "The sku is required.")
    @Size(min = 3, max = 64, message = "The sku must be between 3 and 64 characters.")
    private String sku;

    @NotNull(message = "The price is required.")
    @DecimalMin(value = "0.00", message = "The price must be at least 0.")
    @DecimalMax(value = "99999.00", message = "The price must not exceed 99999.00.")
    @Digits(integer = 5, fraction = 2, message = "The price must have up to 3 digits and 2 decimal places.")
    private BigDecimal price;


    @NotNull(message = "The weightKg is required.")
    @DecimalMin(value = "0.02", message = "The weightKg must be at least 0.02.")
    @DecimalMax(value = "3.00", message = "The weightKg must not exceed 3.00.")
    @Digits(integer = 1, fraction = 2, message = "The weightKg must have up to 1 digits and 2 decimal places.")
    private BigDecimal weightKg;

    @NotBlank(message = "The productColorUrl is required.")
    @Size(min = 10, max = 2000, message = "The productColorUrl must be between 10 and 2000 characters.")
    private String productColorUrl;

    @NotNull(message = "The stock is required.")
    @PositiveOrZero(message = "The stock must be zero or positive value.")
    private Integer stock;

    @NotNull(message = "The productId is required.")
    @Min(value = 1, message = "The productId must be greater than or equal to 1.")
    private Long productId;

    @NotNull(message = "The baseColorId is required.")
    @Min(value = 1, message = "The baseColorID must be greater than or equal to 1.")
    private Long baseColorId;

    @NotNull(message = "The productSizeId is required.")
    @Min(value = 1, message = "The productSizeId must be greater than or equal to 1.")
    private Long productSizeId;
}
