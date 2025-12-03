package com.mendezyahir.product_inventory.repository;

import com.mendezyahir.product_inventory.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {
}
