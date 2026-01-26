package com.mendezyahir.product_inventory.service;


import com.mendezyahir.product_inventory.dto.productDto.ProductRequestDto;
import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantRequestDto;
import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantResponseDto;
import com.mendezyahir.product_inventory.entity.*;
import com.mendezyahir.product_inventory.mapper.ProductImageVariantMapper;
import com.mendezyahir.product_inventory.repository.ProductImageVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProductImageVariantService {
    private final ProductImageVariantRepository productImageVariantRepository;
    private final ProductImageVariantMapper productImageVariantMapper;
    private final ProductImageService productImageService;
    public ProductImageVariantResponseDto createProductImageVariant(ProductImageVariantRequestDto productImageVariantRequestDto){

        ProductImageVariant productImageVariant = this.productImageVariantMapper.toEntity(productImageVariantRequestDto);
        this.attachRelationsToProduct(productImageVariant,productImageVariantRequestDto);
        ProductImageVariant productImageVariantSaved = this.productImageVariantRepository.save(productImageVariant);

        return this.productImageVariantMapper.toResponse(productImageVariantSaved);
    }

    public Page<ProductImageVariantResponseDto> getAllProductImageVariants(int page, int size){
        Page<ProductImageVariant> productImageVariants = this.productImageVariantRepository.findAll(PageRequest.of(page,size));
        return this.productImageVariantMapper.toPageResponse(productImageVariants);
    }

    public ProductImageVariantResponseDto getProductImageVariant(Long id){
        ProductImageVariant productImageVariant = this.productImageVariantRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product image variant with id '"+id+"' not found."));
        return this.productImageVariantMapper.toResponse(productImageVariant);
    }

    public ProductImageVariantResponseDto updateProductImageVariant(Long id, ProductImageVariantRequestDto productImageVariantRequestDto){
        ProductImageVariant productImageVariant = this.productImageVariantMapper.toEntity(productImageVariantRequestDto);
        productImageVariant.setId(id);
        if(!this.productImageVariantRepository.existsById(id)){
            throw new EntityNotFoundException("Product with id '"+id+"' not found.");
        }
        ProductImageVariant updatedProductImageVariant = this.productImageVariantRepository.save(productImageVariant);
        return this.productImageVariantMapper.toResponse(updatedProductImageVariant);
    }

    public void deleteProductImageVariant(Long id){
        if(!this.productImageVariantRepository.existsById(id)){
            throw new EntityNotFoundException("Product image variant with id '"+id+"' not found.");
        }
        this.productImageVariantRepository.deleteById(id);
    }

    //

    public ProductImageVariant getProductImageVariantEntity(Long id){
        return this.productImageVariantRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product image variant with id '"+id+"' not found."));
    }

    public void attachRelationsToProduct(ProductImageVariant productImageVariant, ProductImageVariantRequestDto productImageVariantRequestDto){
        //--
        //
        ProductImage productImage = this.productImageService.getProductImageEntity(productImageVariantRequestDto.getProductImageId());
        productImageVariant.setProductImage(productImage);
        //--
    }


}
