package com.mendezyahir.product_inventory.mapper;

import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryResponseDto;
import com.mendezyahir.product_inventory.dto.productDto.ProductRequestDto;
import com.mendezyahir.product_inventory.dto.productDto.ProductResponseDto;
import com.mendezyahir.product_inventory.entity.Product;
import com.mendezyahir.product_inventory.entity.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "productBrand", ignore = true)
    @Mapping(target = "productCategories", ignore = true)
    Product toEntity(ProductRequestDto requestDto);


    @Mapping(source = "productCategories", target = "productCategories")
    ProductResponseDto toResponse(Product product);

    List<ProductResponseDto> toListResponse(Page<Product> products); //page to List

    default Page<ProductResponseDto> toPageResponse(Page<Product> products){ //page to List DTOs
        List<ProductResponseDto> content = toListResponse(products);
        return new PageImpl<>(
                content,
                products.getPageable(),
                products.getTotalElements()
        );
    }

    default Long SetCategoriesToSetCategoryIds(ProductCategory productCategory){
        return productCategory == null ? null : productCategory.getId();
    }
}

