package com.mendezyahir.product_inventory.controller;

import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryRequestDto;
import com.mendezyahir.product_inventory.dto.productCategoryDto.ProductCategoryResponseDto;
import com.mendezyahir.product_inventory.service.ProductCategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/productCategories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<?> createProductCategory(@Valid @RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        try{
            ProductCategoryResponseDto productCategoryResponseDto = this.productCategoryService.createProductCategory(productCategoryRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productCategoryResponseDto);
        }catch(IllegalStateException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageError);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductCategoryResponseDto>> getAllProductCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<ProductCategoryResponseDto> productResponseDTOs = this.productCategoryService.getAllProductCategories(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductCategory(@PathVariable Long id){
        try{
            ProductCategoryResponseDto productCategory = this.productCategoryService.getProductCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(productCategory);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(messageError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductCategory(@PathVariable Long id, @Valid @RequestBody ProductCategoryRequestDto productCategoryRequestDto){
        try{
            ProductCategoryResponseDto productCategory = this.productCategoryService.updateProductCategory(id, productCategoryRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(productCategory);
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            this.productCategoryService.deleteProductCategory(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }


}
