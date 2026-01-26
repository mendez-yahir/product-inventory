package com.mendezyahir.product_inventory.controller;



import com.mendezyahir.product_inventory.dto.productDto.ProductRequestDto;
import com.mendezyahir.product_inventory.dto.productDto.ProductResponseDto;
import com.mendezyahir.product_inventory.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto productResponseDto= this.productService.createProduct(productRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<ProductResponseDto> productResponseDTOs = this.productService.getAllProducts(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getProduct(@PathVariable Long id){
        try{
            ProductResponseDto product = this.productService.getProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@Valid  @RequestBody ProductRequestDto productRequestDto){
        try{
            ProductResponseDto product = this.productService.updateProduct(id, productRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable  Long id){
        try{
            this.productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }
}


