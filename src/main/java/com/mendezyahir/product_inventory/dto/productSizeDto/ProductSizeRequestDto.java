package com.mendezyahir.product_inventory.dto.productSizeDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSizeRequestDto {
    @NotBlank(message = "The product size label is required.")
    @Size(min = 1, max = 10, message = "The label must be between 1 and 10 characters.")
    private String label;

    @NotBlank(message = "The region is required")
    @Size(min = 1, max = 10, message = "The region must be between 1 and 10 characters.")
    private String region;

    @NotBlank(message = "The numericEquivalent is required.")
    @Size(min = 1, max = 10, message = "The numericEquivalent must be between 1 and 10 characters.")
    private String numericEquivalent;
}
