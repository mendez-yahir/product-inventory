package com.mendezyahir.product_inventory.dto.productBrandDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductBrandRequestDto {
    @NotBlank(message = "The product brand name is required.")
    @Size(min = 3, max = 100, message = "The product brand name must be between 3 and 100 characters.")
    private String name;

    @NotBlank(message = "The product brand description is required.")
    @Size(min = 50, max = 512,message = "The product brand description must be between 50 and 512 characters.")
    private String description;

    @Size(min = 10,max = 2000, message = "The product brand logo URL must be between 10 and 2000 characters.")
    @URL(message = "The product brand logo URL must be a valid URL.")
    private String logoUrl;
}
