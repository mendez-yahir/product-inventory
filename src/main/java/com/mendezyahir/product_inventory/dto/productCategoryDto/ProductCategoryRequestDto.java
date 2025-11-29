package com.mendezyahir.product_inventory.dto.productCategoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryRequestDto {
    @NotBlank(message = "The product category name is required.")
    @Size(min = 3, max = 100, message = "The product category name must be between 3 and 100 characters.")
    private String name;

    @NotBlank(message = "The product category description is required.")
    @Size(min = 20, max = 512, message = "The product category description must be between 20 and 512 characters.")
    private String description;

    @Size(min = 10, max = 2000, message = "The size URL must be between 10 and 2000 characters.")
    @URL(message = "The size guide URL must be a valid URL.")
    private String sizeGuideUrl;
}
