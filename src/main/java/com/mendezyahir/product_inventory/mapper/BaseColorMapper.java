package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorRequestDto;
import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorResponseDto;
import com.mendezyahir.product_inventory.entity.BaseColor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseColorMapper {
    BaseColor toEntity(BaseColorRequestDto requestDto);
    BaseColorResponseDto toResponse(BaseColor baseColor);
}
