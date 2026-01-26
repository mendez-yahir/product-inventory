package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageRequestDto;
import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageResponseDto;
import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageRequestDto;
import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageResponseDto;
import com.mendezyahir.product_inventory.entity.ProductImage;
import com.mendezyahir.product_inventory.entity.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {
    @Mapping(target = "id", ignore = true)
    ProductImage toEntity(ProductImageRequestDto requestDto);
    ProductImageResponseDto toResponse(ProductImage productImage);
    List<ProductImageResponseDto> toListResponse(Page<ProductImage> productImage);

    default Page<ProductImageResponseDto> toPageResponse(Page<ProductImage> productPageImage){
        List<ProductImageResponseDto> content = toListResponse(productPageImage);
        return new PageImpl<>(
                content,
                productPageImage.getPageable(),
                productPageImage.getTotalElements()
        );
    }
}