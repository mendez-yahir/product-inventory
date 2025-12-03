package com.mendezyahir.product_inventory.dto.productImageVariantDto;

import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Setter
@AllArgsConstructor
@Getter
public class ProductImageVariantRequestDto {
    @NotNull(message = "The productImageId is required.")
    @Min(value = 1, message = "The productImageId must be greater than or equal to 1.")
    private Long productImageId;

    @NotBlank(message = "The URL is required.")
    @Size(min = 10, max = 2000, message = "The URL must be between 10 and 2000 characters.")
    @URL(message = "The URL must be a valid URL.")
    private String url;

    @Min(value = 100, message = "The width must be greater than or equal to 100.")
    @Max(value = 2048, message = "The width must be less than or equal to 2048.")
    private int width;

    @Min(value = 100, message = "The height must be greater than or equal to 100.")
    @Max(value = 2048, message = "The height must be less than or equal to 2048.")
    private int height;

    @NotBlank(message = "The resolution is required.")
    @Size(min = 1, max = 5, message = "The resolution must be between 1 and 5 characters." )
    private String resolution;
}
