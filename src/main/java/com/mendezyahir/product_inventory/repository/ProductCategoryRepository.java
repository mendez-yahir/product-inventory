package com.mendezyahir.product_inventory.repository;

import com.mendezyahir.product_inventory.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>{
    boolean existsByName(String name);
}
