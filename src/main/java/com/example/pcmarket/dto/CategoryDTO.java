package com.example.pcmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    @NotNull(message = "name ustuni bo'sh bo'lishi mumkin emas")
    private String name;
}
