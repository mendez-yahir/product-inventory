package com.mendezyahir.product_inventory.controller;

import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorRequestDto;
import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorResponseDto;
import com.mendezyahir.product_inventory.service.BaseColorService;
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
@RequestMapping("/api/v1/baseColor")
@RestController
public class BaseColorController {
    private final BaseColorService baseColorService;

    @PostMapping
    public ResponseEntity<?> createBaseColor(@Valid @RequestBody BaseColorRequestDto baseColorRequestDto){
        try{
            BaseColorResponseDto baseColorResponseDto = this.baseColorService.createBaseColor(baseColorRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(baseColorResponseDto);
        }catch(IllegalStateException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(messageError);
        }
    }
    @GetMapping
    public ResponseEntity<Page<BaseColorResponseDto>> getAllBaseColors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ){
        Page<BaseColorResponseDto> productResponseDTOs = this.baseColorService.getAllBaseColors(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBaseColor(@PathVariable Long id){
        try{
            BaseColorResponseDto productBrand = this.baseColorService.getBaseColor(id);
            return ResponseEntity.status(HttpStatus.OK).body(productBrand);
        }catch(EntityNotFoundException e){
            Map<String, Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(messageError);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBaseColor(@PathVariable Long id, @Valid @RequestBody BaseColorRequestDto baseColorRequestDto){
        try{
            BaseColorResponseDto baseColor = this.baseColorService.updateBaseColor(baseColorRequestDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(baseColor);
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBaseColor(@PathVariable Long id){
        try{
            this.baseColorService.deleteBaseColor(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(EntityNotFoundException e){
            Map<String,Object> messageError = new HashMap<>();
            messageError.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageError);
        }
    }
}

