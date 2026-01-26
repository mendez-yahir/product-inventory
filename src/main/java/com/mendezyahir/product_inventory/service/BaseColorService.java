package com.mendezyahir.product_inventory.service;

import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorRequestDto;
import com.mendezyahir.product_inventory.dto.baseColorDto.BaseColorResponseDto;
import com.mendezyahir.product_inventory.entity.BaseColor;
import com.mendezyahir.product_inventory.entity.Product;
import com.mendezyahir.product_inventory.mapper.BaseColorMapper;
import com.mendezyahir.product_inventory.repository.BaseColorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BaseColorService{
    private final BaseColorRepository baseColorRepository;
    private final BaseColorMapper baseColorMapper;

    public BaseColorResponseDto createBaseColor(BaseColorRequestDto baseColorRequestDto){
        BaseColor baseColor = this.baseColorMapper.toEntity(baseColorRequestDto);
        baseColor.setName(baseColor.getName().toLowerCase());
        baseColor.setHexCode(baseColor.getHexCode().toLowerCase());
        if(this.baseColorRepository.existsByHexCode(baseColor.getHexCode())){
            throw new IllegalStateException("The baseColor with hexCode '"+"' already exists.");
        }
        if(this.baseColorRepository.existsByName(baseColor.getName())){
            throw new IllegalStateException("The baseColor with name '"+"' already exists.");
        }

        BaseColor baseColorSaved = this.baseColorRepository.save(baseColor);

        return this.baseColorMapper.toResponse(baseColorSaved);
    }

    public Page<BaseColorResponseDto> getAllBaseColors(int page, int size){
        Page<BaseColor> baseColors = this.baseColorRepository.findAll(PageRequest.of(page,size));
        return this.baseColorMapper.toPageResponse(baseColors);
    }

    public BaseColorResponseDto getBaseColor(Long id){
        BaseColor baseColor = this.baseColorRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("baseColor with id '"+"' not found."));
        return this.baseColorMapper.toResponse(baseColor);
    }

    public BaseColorResponseDto updateBaseColor(BaseColorRequestDto baseColorRequestDto, Long id){
        if(!this.baseColorRepository.existsById(id)){
            throw new EntityNotFoundException("baseColor with id '"+"' not found.");
        }

        BaseColor baseColor = this.baseColorMapper.toEntity(baseColorRequestDto);
        baseColor.setId(id);

        BaseColor baseColorSaved = this.baseColorRepository.save(baseColor);

        return this.baseColorMapper.toResponse(baseColorSaved);
    }

    public void deleteBaseColor(Long id){
        if(!this.baseColorRepository.existsById(id)){
            throw new EntityNotFoundException("baseColor with id '"+"' not found.");
        }
        this.baseColorRepository.deleteById(id);
    }

    public BaseColor getBaseColorEntity(Long id){
        return this.baseColorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("BaseColor with id '"+id+"' not found."));
    }
}
