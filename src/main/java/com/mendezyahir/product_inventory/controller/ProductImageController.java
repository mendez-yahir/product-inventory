package com.mendezyahir.product_inventory.controller;

import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageRequestDto;
import com.mendezyahir.product_inventory.dto.productImageDto.ProductImageResponseDto;
import com.mendezyahir.product_inventory.service.ProductImageService;
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
@RequestMapping("/api/v1/productImages")
public class ProductImageController {
    private final ProductImageService productImageService;

    @PostMapping
    public ResponseEntity<?> createProductImage(@Valid @RequestBody ProductImageRequestDto productImageRequestDto){
        try{
            ProductImageResponseDto productImageResponseDto = this.productImageService.createProductImage(productImageRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productImageResponseDto);
        }catch(IllegalStateException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageError);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductImageResponseDto>> getAllProductImages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<ProductImageResponseDto> productResponseDTOs = this.productImageService.getAllProductImages(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductImage(@PathVariable Long id){
        try{
            ProductImageResponseDto productImage = this.productImageService.getProductImage(id);
            return ResponseEntity.status(HttpStatus.OK).body(productImage);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(messageError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductImage(@PathVariable Long id, @Valid @RequestBody ProductImageRequestDto productImageRequestDto){
        try{
            ProductImageResponseDto productImage = this.productImageService.updateProductImage(id, productImageRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(productImage);
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductImage(@PathVariable Long id){
        try{
            this.productImageService.deleteProductImage(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }
}
