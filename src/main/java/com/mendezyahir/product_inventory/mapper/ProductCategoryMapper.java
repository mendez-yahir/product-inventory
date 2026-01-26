package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryRequestDto;
import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryResponseDto;
import com.mendezyahir.product_inventory.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductCategoryMapper {
    @Mapping(target = "id", ignore = true)
    ProductCategory toEntity(ProductCategoryRequestDto requestDto);
    ProductCategoryResponseDto toResponse(ProductCategory productCategory);
    List<ProductCategoryResponseDto> toListResponse(Page<ProductCategory> productCategories);

    default Page<ProductCategoryResponseDto> toPageResponse(Page<ProductCategory> productPageCategories){
        List<ProductCategoryResponseDto> content  = toListResponse(productPageCategories);
        return new PageImpl<>(
                content,
                productPageCategories.getPageable(),
                productPageCategories.getTotalElements()
        );
    };
}
