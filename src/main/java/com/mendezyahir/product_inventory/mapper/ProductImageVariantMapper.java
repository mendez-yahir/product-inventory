package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantRequestDto;
import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantResponseDto;
import com.mendezyahir.product_inventory.entity.ProductImageVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageVariantMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productImage", ignore = true)
    ProductImageVariant toEntity(ProductImageVariantRequestDto requestDto);

    @Mapping(target = "productImageId", source = "productImage.id")
    ProductImageVariantResponseDto toResponse(ProductImageVariant productImageVariant);

    List<ProductImageVariantResponseDto> toListResponse(Page<ProductImageVariant> listProductImageVariant);

    default Page<ProductImageVariantResponseDto> toPageResponse(Page<ProductImageVariant> pageProductImageVariant){
        List<ProductImageVariantResponseDto> content = toListResponse(pageProductImageVariant);
        return new PageImpl<>(
                content,
                pageProductImageVariant.getPageable(),
                pageProductImageVariant.getTotalElements()
        );
    }

}
