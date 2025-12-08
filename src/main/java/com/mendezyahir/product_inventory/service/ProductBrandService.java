package com.mendezyahir.product_inventory.service;

import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandRequestDto;
import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandResponseDto;
import com.mendezyahir.product_inventory.entity.ProductBrand;
import com.mendezyahir.product_inventory.mapper.ProductBrandMapper;
import com.mendezyahir.product_inventory.repository.ProductBrandRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductBrandService {
    private final ProductBrandRepository productBrandRepository;
    private final ProductBrandMapper productBrandMapper;

    public ProductBrandResponseDto createProductBrand(ProductBrandRequestDto productRequestDto){
        ProductBrand  productBrand = this.productBrandMapper.toEntity(productRequestDto);
        //name to lowercase trim
        productBrand.setName(productBrand.getName().trim().toLowerCase());
        //description trim
        productBrand.setDescription(productBrand.getDescription().trim());

        if(this.productBrandRepository.existsByName(productBrand.getName())){
            throw new IllegalStateException("A product brand with the name '"+productBrand.getName()+"' already exists");
        }

        ProductBrand productBrandSaved = this.productBrandRepository.save(productBrand);

        return this.productBrandMapper.toResponse(productBrandSaved);
    }

    public Page<ProductBrandResponseDto> getAllProductBrands(int page, int size){
        Page<ProductBrand> productBrands = this.productBrandRepository.findAll(PageRequest.of(page,size));
        return this.productBrandMapper.toPageResponse(productBrands);
    }

    public ProductBrandResponseDto getProductBrand(Long id){
        ProductBrand productBrand = this.productBrandRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product brand with id '"+id+"' not found."));
        return this.productBrandMapper.toResponse(productBrand);
    }

    public ProductBrandResponseDto updateProductBrand(Long id, ProductBrandRequestDto productBrandRequestDto){
        ProductBrand productBrand = this.productBrandMapper.toEntity(productBrandRequestDto);
        productBrand.setId(id);
        if(!this.productBrandRepository.existsById(id)){
            throw new EntityNotFoundException("Product with id '"+id+"' not found.");
        }
        ProductBrand updatedProductBrand = this.productBrandRepository.save(productBrand);
        return this.productBrandMapper.toResponse(updatedProductBrand);
    }

    public void deleteProductBrand(Long id){
        if(!this.productBrandRepository.existsById(id)){
            throw new EntityNotFoundException("Product brand with id '"+id+"' not found.");
        }
        this.productBrandRepository.deleteById(id);
    }

    //

    public ProductBrand getProductBrandEntity(Long id){
        return this.productBrandRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product brand with id '"+id+"' not found."));
    }
}
