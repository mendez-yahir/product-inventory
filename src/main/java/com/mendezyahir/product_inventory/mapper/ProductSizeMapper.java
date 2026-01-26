package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeRequestDto;
import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeResponseDto;
import com.mendezyahir.product_inventory.entity.ProductSize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductSizeMapper {
    @Mapping(target = "id", ignore = true)
    ProductSize toEntity(ProductSizeRequestDto requestDto);
    ProductSizeResponseDto toResponse(ProductSize productSize);

    List<ProductSizeResponseDto> toListResponse(Page<ProductSize> pageProductSizes);

    default Page<ProductSizeResponseDto> toPageResponse(Page<ProductSize> pageProductSize){
        List<ProductSizeResponseDto> content = toListResponse(pageProductSize);
        return new PageImpl<>(
                content,
                pageProductSize.getPageable(),
                pageProductSize.getTotalElements()
        );
    }
}
