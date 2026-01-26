package com.mendezyahir.product_inventory.service;


import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageRequestDto;
import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageResponseDto;
import com.mendezyahir.product_inventory.entity.ProductImage;
import com.mendezyahir.product_inventory.mapper.ProductImageMapper;
import com.mendezyahir.product_inventory.repository.ProductImageRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductImageMapper productImageMapper;

    public ProductImageResponseDto createProductImage(ProductImageRequestDto productRequestDto){

        ProductImage productImage = this.productImageMapper.toEntity(productRequestDto);

        //AltText trim
        productImage.setAltText(productImage.getAltText().trim());

        ProductImage productImageSaved = this.productImageRepository.save(productImage);

        return this.productImageMapper.toResponse(productImageSaved);
    }

    public Page<ProductImageResponseDto> getAllProductImages(int page, int size){
        Page<ProductImage> productImages = this.productImageRepository.findAll(PageRequest.of(page,size));
        return this.productImageMapper.toPageResponse(productImages);
    }

    public ProductImageResponseDto getProductImage(Long id){
        ProductImage productImage = this.productImageRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product image with id '"+id+"' not found."));
        return this.productImageMapper.toResponse(productImage);
    }

    public ProductImageResponseDto updateProductImage(Long id, ProductImageRequestDto productImageRequestDto){
        ProductImage productImage = this.productImageMapper.toEntity(productImageRequestDto);
        productImage.setId(id);
        if(!this.productImageRepository.existsById(id)){
            throw new EntityNotFoundException("Product with id '"+id+"' not found.");
        }
        ProductImage updatedProductImage = this.productImageRepository.save(productImage);
        return this.productImageMapper.toResponse(updatedProductImage);
    }

    public void deleteProductImage(Long id){
        if(!this.productImageRepository.existsById(id)){
            throw new EntityNotFoundException("Product image with id '"+id+"' not found.");
        }
        this.productImageRepository.deleteById(id);
    }

    //

    public ProductImage getProductImageEntity(Long id){
        return this.productImageRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product image with id '"+id+"' not found."));
    }
}
