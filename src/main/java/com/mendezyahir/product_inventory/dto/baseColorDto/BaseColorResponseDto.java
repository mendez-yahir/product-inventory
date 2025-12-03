package com.mendezyahir.product_inventory.dto.baseColorDto;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
public class BaseColorResponseDto {
    private Long id;
    private String name;
    private String hexCode;
}

