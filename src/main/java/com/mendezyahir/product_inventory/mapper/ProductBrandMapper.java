package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandRequestDto;
import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandResponseDto;
import com.mendezyahir.product_inventory.entity.ProductBrand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductBrandMapper {
    @Mapping(target = "id", ignore = true)
    ProductBrand toEntity(ProductBrandRequestDto requestDto);

    ProductBrandResponseDto toResponse(ProductBrand productBrand);
    List<ProductBrandResponseDto> toListResponse(Page<ProductBrand> productBrand);

    default Page<ProductBrandResponseDto> toPageResponse(Page<ProductBrand> productPageBrand){
        List<ProductBrandResponseDto> content = toListResponse(productPageBrand);
        return new PageImpl<>(
                content,
                productPageBrand.getPageable(),
                productPageBrand.getTotalElements()
        );
    }
}
