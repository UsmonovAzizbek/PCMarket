package com.example.pcmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    @NotNull(message = "name ustuni bo'sh bo'lishi mumkin emas")
    private String name;
    @NotNull(message = "description ustuni bo'sh bo'lishi mumkin emas")
    private String description;
    @NotNull(message = "price ustuni bo'sh bo'lishi mumkin emas")
    private Integer price;
    @NotNull(message = "category_id ustuni bo'sh bo'lishi mumkin emas")
    private Integer category_id;
}
