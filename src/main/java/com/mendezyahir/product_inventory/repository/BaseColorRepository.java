package com.mendezyahir.product_inventory.repository;

import com.mendezyahir.product_inventory.entity.BaseColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseColorRepository extends JpaRepository<BaseColor, Long>{
    boolean existsByName(String name);
    boolean existsByHexCode(String hexCode);
}
