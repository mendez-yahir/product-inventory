package com.mendezyahir.product_inventory.controller;

import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandRequestDto;
import com.mendezyahir.product_inventory.dto.productBrandDto.ProductBrandResponseDto;
import com.mendezyahir.product_inventory.service.ProductBrandService;
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
@RequestMapping("/api/v1/productBrands")
public class ProductBrandController {
    private final ProductBrandService productBrandService;

    @PostMapping
    public ResponseEntity<?> createProductBrand(@Valid @RequestBody ProductBrandRequestDto productBrandRequestDto){
        try{
            ProductBrandResponseDto productBrandResponseDto = this.productBrandService.createProductBrand(productBrandRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productBrandResponseDto);
        }catch(IllegalStateException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageError);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductBrandResponseDto>> getAllProductBrands(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<ProductBrandResponseDto> productResponseDTOs = this.productBrandService.getAllProductBrands(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductBrand(@PathVariable Long id){
        try{
            ProductBrandResponseDto productBrand = this.productBrandService.getProductBrand(id);
            return ResponseEntity.status(HttpStatus.OK).body(productBrand);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(messageError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductBrand(@PathVariable Long id, @Valid @RequestBody ProductBrandRequestDto productBrandRequestDto){
        try{
            ProductBrandResponseDto productBrand = this.productBrandService.updateProductBrand(id, productBrandRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(productBrand);
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            this.productBrandService.deleteProductBrand(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }


}
