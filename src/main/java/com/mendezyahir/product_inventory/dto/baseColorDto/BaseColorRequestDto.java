package com.mendezyahir.product_inventory.dto.baseColorDto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BaseColorRequestDto {
    @NotBlank(message = "The name is required.")
    @Size(min = 3, max = 50, message = "The name must be between 3 and 50 characters.")
    private String name;

    @NotBlank(message = "The hexCode is required.")
    @Size(min = 3, max = 9, message = "The hexCode must be between 3 and 9 characters.")
    private String hexCode;
}
