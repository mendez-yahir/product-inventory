package com.mendezyahir.product_inventory.service;


import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryRequestDto;
import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryResponseDto;
import com.mendezyahir.product_inventory.entity.ProductCategory;
import com.mendezyahir.product_inventory.mapper.ProductCategoryMapper;
import com.mendezyahir.product_inventory.repository.ProductCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryResponseDto createProductCategory(ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategory productCategory = this.productCategoryMapper.toEntity(productCategoryRequestDto);
        //name to lowercase trim
        productCategory.setName(productCategory.getName().trim().toLowerCase());
        //description trim
        productCategory.setDescription(productCategory.getDescription().trim());

        if(this.productCategoryRepository.existsByName(productCategory.getName())){
            throw new IllegalStateException("A product Category with the name '"+productCategory.getName()+"' already exists");
        }

        ProductCategory productCategorySaved = this.productCategoryRepository.save(productCategory);

        return this.productCategoryMapper.toResponse(productCategorySaved);
    }

    public Page<ProductCategoryResponseDto> getAllProductCategories(int page, int size){
        Page<ProductCategory> productCategories = this.productCategoryRepository.findAll(PageRequest.of(page,size));
        return this.productCategoryMapper.toPageResponse(productCategories);
    }

    public ProductCategoryResponseDto getProductCategory(Long id){
        ProductCategory productCategory = this.productCategoryRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product Category with id '"+id+"' not found."));
        return this.productCategoryMapper.toResponse(productCategory);
    }

    public ProductCategoryResponseDto updateProductCategory(Long id, ProductCategoryRequestDto productCategoryRequestDto){
        ProductCategory productCategory = this.productCategoryMapper.toEntity(productCategoryRequestDto);
        productCategory.setId(id);
        if(!this.productCategoryRepository.existsById(id)){
            throw new EntityNotFoundException("Product Category with id '"+id+"' not found.");
        }
        ProductCategory updatedProductCategory = this.productCategoryRepository.save(productCategory);
        return this.productCategoryMapper.toResponse(updatedProductCategory);
    }

    public void deleteProductCategory(Long id){
        if(!this.productCategoryRepository.existsById(id)){
            throw new EntityNotFoundException("Product Category with id '"+id+"' not found.");
        }
        this.productCategoryRepository.deleteById(id);
    }

    //

    public ProductCategory getProductCategoryEntity(Long id){
        return this.productCategoryRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product Category with id '"+id+"' not found."));
    }
}

