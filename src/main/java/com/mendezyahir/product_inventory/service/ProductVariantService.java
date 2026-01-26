package com.mendezyahir.product_inventory.service;

import com.mendezyahir.product_inventory.dto.productVariantDto.ProductVariantRequestDto;
import com.mendezyahir.product_inventory.dto.productVariantDto.ProductVariantResponseDto;
import com.mendezyahir.product_inventory.entity.ProductSize;
import com.mendezyahir.product_inventory.entity.ProductVariant;
import com.mendezyahir.product_inventory.mapper.ProductVariantMapper;
import com.mendezyahir.product_inventory.repository.ProductVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductVariantService {
    private final ProductVariantRepository productVariantRepository;
    private final ProductVariantMapper productVariantMapper;
    private final ProductService productService;
    private final BaseColorService baseColorService;
    private final ProductSizeService productSizeService;

    @Transactional
    public ProductVariantResponseDto createProductVariant(ProductVariantRequestDto productVariantRequestDto){
        ProductVariant productVariant = this.productVariantMapper.toEntity(productVariantRequestDto);
        return getProductVariantResponseDto(productVariantRequestDto, productVariant);
    }

    public ProductVariantResponseDto getProductVariantById(Long id){
        ProductVariant productVariant = this.productVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product variant with id '"+id+"' not found."));
        return this.productVariantMapper.toResponse(productVariant);
    }

    public Page<ProductVariantResponseDto> listProductVariants(int page, int size){
        Page<ProductVariant> listProductVariants = this.productVariantRepository.findAll(PageRequest.of(page,size));
        return this.productVariantMapper.toPageResponse(listProductVariants);
    }

    public void deleteProductVariant(Long id){
        if(!this.productVariantRepository.existsById(id)){
            throw new EntityNotFoundException("Product variant with id '"+id+"' not found.");
        }
        this.productVariantRepository.deleteById(id);
    }

    public ProductVariantResponseDto updateProductVariant(Long id, ProductVariantRequestDto productVariantRequestDto){
        if(!this.productVariantRepository.existsById(id)){
            throw new EntityNotFoundException("Product variant with id '"+id+"' not found.");
        }
        ProductVariant productVariant = this.productVariantMapper.toEntity(productVariantRequestDto);
        productVariant.setId(id);

        return getProductVariantResponseDto(productVariantRequestDto, productVariant);
    }

    //
    private ProductVariantResponseDto getProductVariantResponseDto(ProductVariantRequestDto productVariantRequestDto, ProductVariant productVariant) {
        productVariant.setProduct(this.productService.getProductEntity(productVariantRequestDto.getProductId()));
        productVariant.setBaseColor(this.baseColorService.getBaseColorEntity(productVariantRequestDto.getBaseColorId()));
        productVariant.setProductSize(this.productSizeService.getProductSizeEntity(productVariantRequestDto.getProductSizeId()));

        ProductVariant productVariantSaved = this.productVariantRepository.save(productVariant);
        return this.productVariantMapper.toResponse(productVariantSaved);
    }
}
