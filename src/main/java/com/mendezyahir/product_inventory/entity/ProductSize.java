package com.mendezyahir.product_inventory.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "product_sizes",
        uniqueConstraints = @UniqueConstraint(
            columnNames = {"label","region","numericEquivalent"}
    )
)

public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String label;

    @Column(nullable = false, length = 10)
    private String region;

    @Column(nullable = false, length = 10)
    private String numericEquivalent;
}
