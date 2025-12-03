package com.mendezyahir.product_inventory.repository;

import com.mendezyahir.product_inventory.entity.ProductImageVariant;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductImageVariantRepository extends JpaRepository<ProductImageVariant, Long> {
}
