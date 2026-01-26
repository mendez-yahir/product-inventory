package com.mendezyahir.product_inventory.controller;

import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeRequestDto;
import com.mendezyahir.product_inventory.dto.productSizeDto.ProductSizeResponseDto;
import com.mendezyahir.product_inventory.service.ProductSizeService;
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
@RequestMapping("/api/v1/productSizes")
public class ProductSizeController {
    private final ProductSizeService productSizeService;

    @PostMapping
    public ResponseEntity<?> createProductSize(@Valid @RequestBody ProductSizeRequestDto productSizeRequestDto){
        try{
            ProductSizeResponseDto productSizeResponseDto = this.productSizeService.createProductSize(productSizeRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productSizeResponseDto);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("error",HttpStatus.NOT_FOUND);
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }catch(IllegalStateException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("error",HttpStatus.CONFLICT);
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageError);
        }catch(Exception e){
            Map<String, String> messageError = new HashMap<>();
            messageError.put("error",String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            messageError.put("message","Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductSizeById(@PathVariable Long id){
        try{
            ProductSizeResponseDto productSizeResponseDto = this.productSizeService.getProductSizeById(id);
            return ResponseEntity.status(HttpStatus.OK).body(productSizeResponseDto);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("error",HttpStatus.NOT_FOUND);
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }catch(Exception e){
            Map<String, String> messageError = new HashMap<>();
            messageError.put("error",String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            messageError.put("message","Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
        }
    }

    @GetMapping
    public ResponseEntity<?> listProductSizes(
            @RequestParam int page,
            @RequestParam int size
    ){
        try{
            Page<ProductSizeResponseDto> pageProductSizeResponseDto = this.productSizeService.listProductSizes(page,size);
            return ResponseEntity.status(HttpStatus.OK).body(pageProductSizeResponseDto);
        }catch(Exception e){
            Map<String, String> messageError = new HashMap<>();
            messageError.put("error",String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            messageError.put("message","Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProductSize(
            @PathVariable Long id,
            @Valid @RequestBody ProductSizeRequestDto productSizeRequestDto
    ){
        try{
            ProductSizeResponseDto productSizeResponseDto = this.productSizeService.updateProductSize(id,productSizeRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body(productSizeResponseDto);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("error",HttpStatus.NOT_FOUND);
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }catch(Exception e){
            Map<String, String> messageError = new HashMap<>();
            messageError.put("error",String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            messageError.put("message","Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductSize(@PathVariable Long id){
        try{
            this.productSizeService.deleteProductSize(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("error",HttpStatus.NOT_FOUND);
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }catch(Exception e){
            Map<String, String> messageError = new HashMap<>();
            messageError.put("error",String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            messageError.put("message","Internal server error.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(messageError);
        }
    }
}
