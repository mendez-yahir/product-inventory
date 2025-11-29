package com.mendezyahir.product_inventory.dto.productDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = "The product name is required.")
    @Size(min = 3, max = 255, message = "The product name must be between 3 and 255 characters.")
    private String name;

    @NotBlank(message = "The product description is required.")
    @Size(min = 50, max = 2000, message = "The product description must be between 50 and 2000 characters.")
    private String description;

    @NotNull(message = "The product brand ID is required.")
    private Long productBrandId;

    @NotEmpty(message = "At least one category ID is required.")
    @Size(max = 5, message = "A product cannot have  more than 5 categories.")
    private Set<Long> productCategoryIds;
}
