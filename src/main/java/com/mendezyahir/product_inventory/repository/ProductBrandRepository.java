package com.mendezyahir.product_inventory.repository;

import com.mendezyahir.product_inventory.entity.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long>{
    boolean existsByName(String name);
}
