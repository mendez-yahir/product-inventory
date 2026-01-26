package com.mendezyahir.product_inventory.controller;

import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantRequestDto;
import com.mendezyahir.product_inventory.dto.productImageVariantDto.ProductImageVariantResponseDto;
import com.mendezyahir.product_inventory.service.ProductImageVariantService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/productImageVariants")
public class ProductImageVariantController {
    private final ProductImageVariantService productImageVariantService;

    @PostMapping
    public ResponseEntity<?> createProductImageVariant(@Valid @RequestBody ProductImageVariantRequestDto productImageVariantRequestDto){
        try{
            ProductImageVariantResponseDto productImageVariantResponseDto = this.productImageVariantService.createProductImageVariant(productImageVariantRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productImageVariantResponseDto);
        }catch(IllegalStateException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageError);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductImageVariantResponseDto>> getAllProductImageVariants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<ProductImageVariantResponseDto> productResponseDTOs = this.productImageVariantService.getAllProductImageVariants(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductImageVariant(@PathVariable Long id){
        try{
            ProductImageVariantResponseDto productImageVariant = this.productImageVariantService.getProductImageVariant(id);
            return ResponseEntity.status(HttpStatus.OK).body(productImageVariant);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(messageError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductImageVariant(@PathVariable Long id, @Valid @RequestBody ProductImageVariantRequestDto productImageVariantRequestDto){
        try{
            ProductImageVariantResponseDto productImageVariant = this.productImageVariantService.updateProductImageVariant(id, productImageVariantRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(productImageVariant);
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductImageVariant(@PathVariable Long id){
        try{
            this.productImageVariantService.deleteProductImageVariant(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }
}
