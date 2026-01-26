package com.mendezyahir.product_inventory.service;

import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeRequestDto;
import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeResponseDto;
import com.mendezyahir.product_inventory.entity.ProductSize;
import com.mendezyahir.product_inventory.mapper.ProductSizeMapper;
import com.mendezyahir.product_inventory.repository.ProductSizeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductSizeService {
    private final ProductSizeRepository productSizeRepository;
    private final ProductSizeMapper productSizeMapper;


    public ProductSizeResponseDto createProductSize(ProductSizeRequestDto productSizeRequestDto){
        ProductSize productSize = this.productSizeMapper.toEntity(productSizeRequestDto);
        productSize.setLabel(productSize.getLabel().trim().toUpperCase());
        productSize.setRegion(productSize.getRegion().trim().toUpperCase());
        productSize.setNumericEquivalent(productSize.getNumericEquivalent().trim());
        try{
            ProductSize productSizeSaved = this.productSizeRepository.save(productSize);
            return productSizeMapper.toResponse(productSizeSaved);
        }catch(DataIntegrityViolationException e){
            throw new IllegalStateException("The product size already exists.");
        }
    }

    // get product size by id
    public ProductSizeResponseDto getProductSizeById(Long id){
        ProductSize productSize = this.productSizeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The product size with id "+id+" not found."));
        return productSizeMapper.toResponse(productSize);
    }

    // list product size
    public Page<ProductSizeResponseDto> listProductSizes(int page, int size){
        Page<ProductSize> pageProductSizes = this.productSizeRepository.findAll(PageRequest.of(page,size));
        return this.productSizeMapper.toPageResponse(pageProductSizes);
    }

    // update product size
    public ProductSizeResponseDto updateProductSize(Long id, ProductSizeRequestDto productSizeRequestDto){
        if(!this.productSizeRepository.existsById(id)){
            throw new EntityNotFoundException("The product size with id "+id+" not found.");
        }
        ProductSize productSize = this.productSizeMapper.toEntity(productSizeRequestDto);
        productSize.setId(id);
        ProductSize productSizeSaved = this.productSizeRepository.save(productSize);
        return this.productSizeMapper.toResponse(productSizeSaved);
    }

    // delete
    public void deleteProductSize(Long id){
        if(!this.productSizeRepository.existsById(id)){
            throw new EntityNotFoundException("The product size with id "+id+" not found.");
        }
        this.productSizeRepository.deleteById(id);
    }

    public ProductSize getProductSizeEntity(Long id){
        return this.productSizeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("The product size with id "+id+" not found."));
    }
}
