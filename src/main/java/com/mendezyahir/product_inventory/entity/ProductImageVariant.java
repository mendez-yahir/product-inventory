package com.mendezyahir.product_inventory.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "product_image_variant")
public class ProductImageVariant {

    public enum ResolutionType{
        XS, SM, MD, LG, XL,XXL;

        @JsonCreator
        public static ResolutionType from(String value){
            return ResolutionType.valueOf(value.trim().toUpperCase());
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;

    @Column(nullable = false, length = 2000)
    private String url;

    @Column
    private int width;

    @Column
    private int height;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private ResolutionType resolution; //xs - sm - md - lg - xl - xxl
}

