package com.mendezyahir.product_inventory.service;

import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandResponseDto;
import com.mendezyahir.product_inventory.dto.productDto.ProductRequestDto;
import com.mendezyahir.product_inventory.dto.productDto.ProductResponseDto;
import com.mendezyahir.product_inventory.entity.Product;
import com.mendezyahir.product_inventory.entity.ProductBrand;
import com.mendezyahir.product_inventory.entity.ProductCategory;
import com.mendezyahir.product_inventory.mapper.ProductMapper;
import com.mendezyahir.product_inventory.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductBrandService productBrandService;
    private final ProductCategoryService productCategoryService;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto){

        Product product =  this.productMapper.toEntity(productRequestDto);
        // name to loweCase
        product.setName(product.getName().trim().toLowerCase());
        // description trim
        product.setDescription(product.getDescription().trim());

        if(this.productRepository.existsByName(product.getName())){
            throw new EntityNotFoundException("A product with the name '"+product.getName()+"' already exists.");
        }
        //
        this.attachRelationsToProduct(product,productRequestDto);
        //
        Product productSaved = this.productRepository.save(product);

        return this.productMapper.toResponse(productSaved);
    }

    public Page<ProductResponseDto> getAllProducts(int page, int size){
        Page<Product> products = this.productRepository.findAll(PageRequest.of(page, size));
        return this.productMapper.toPageResponse(products);
    }
    public ProductResponseDto getProduct(Long id){
        Product product = this.productRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Product with id '"+id+"' not found."));
        return this.productMapper.toResponse(product);
    }

    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto){
        Product product = this.productMapper.toEntity(productRequestDto);
        product.setId(id);
        if(!this.productRepository.existsById(id)){
            throw new EntityNotFoundException("Product with id '"+id+"' not found.");
        }
        //
        this.attachRelationsToProduct(product,productRequestDto);
        //
        Product updatedProduct = this.productRepository.save(product);
        return this.productMapper.toResponse(updatedProduct);
    }

    public void deleteProduct(Long id){
        if(!this.productRepository.existsById(id)){
            throw new EntityNotFoundException("Product with id '"+id+"' not found.");
        }
        this.productRepository.deleteById(id);
    }

    //
    public void attachRelationsToProduct(Product product, ProductRequestDto productRequestDto){
        //--
        // relation with product brand
        ProductBrand productBrand = this.productBrandService.getProductBrandEntity(productRequestDto.getProductBrandId());
        product.setProductBrand(productBrand);
        // relation with product category
        Set<ProductCategory> productCategories= new HashSet<>();
        for(Long id : productRequestDto.getProductCategoryIds()){
            ProductCategory productCategory = this.productCategoryService.getProductCategoryEntity(id);
            productCategories.add(productCategory);
        }
        product.setProductCategories(productCategories);
        //--
    }
}
