package com.mendezyahir.product_inventory.dto.productImageDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProductImageRequestDto {

    @NotBlank(message = "The altText is required.")
    @Size(min = 10, max = 150, message = "The altText must be between 10 and 150 characters.")
    private String altText;

    @NotNull(message = "The isPrimary must be provided.")
    private Boolean isPrimary;

    @NotBlank(message = "The thumbnailUrl is required.")
    @Size(min = 10, max = 2000, message = "The thumbnailUrl must be between 10 and 2000 characters.")
    @URL(message = "The thumbnailUrl must be a valid URL.")
    private String thumbnailUrl;
}
