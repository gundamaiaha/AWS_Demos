package com.myapps.productspublisher.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDateTime createAt;
    private LocalDateTime lastModifiedAt;
}
