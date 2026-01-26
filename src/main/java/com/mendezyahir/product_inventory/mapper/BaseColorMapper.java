package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorRequestDto;
import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorResponseDto;
import com.mendezyahir.product_inventory.entity.BaseColor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BaseColorMapper {
    @Mapping(target = "id", ignore = true)
    BaseColor toEntity(BaseColorRequestDto requestDto);

    BaseColorResponseDto toResponse(BaseColor baseColor);

    List<BaseColorResponseDto> toListResponse(Page<BaseColor> baseColorPage);

    default Page<BaseColorResponseDto> toPageResponse(Page<BaseColor> baseColorPage){
        List<BaseColorResponseDto> baseColorListResponse = this.toListResponse(baseColorPage);

        return new PageImpl<>(
                baseColorListResponse,
                baseColorPage.getPageable(),
                baseColorPage.getTotalElements()
        );
    };
}
