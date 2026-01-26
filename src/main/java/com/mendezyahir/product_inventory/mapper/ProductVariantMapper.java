package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productVariantDto.ProductVariantRequestDto;
import com.mendezyahir.product_inventory.dto.productVariantDto.ProductVariantResponseDto;
import com.mendezyahir.product_inventory.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "baseColor", ignore = true)
    @Mapping(target = "productSize", ignore = true)
    ProductVariant toEntity(ProductVariantRequestDto requestDto);

    ProductVariantResponseDto toResponse(ProductVariant productVariant);

    List<ProductVariantResponseDto> toListResponse(Page<ProductVariant> productVariant);

    default Page<ProductVariantResponseDto> toPageResponse(Page<ProductVariant> pageProductVariant){
        List<ProductVariantResponseDto> content = toListResponse(pageProductVariant);
        return new PageImpl<>(
                content,
                pageProductVariant.getPageable(),
                pageProductVariant.getTotalElements()
        );
    }
}
