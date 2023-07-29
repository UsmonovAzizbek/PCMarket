package com.example.pcmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    @NotNull(message = "username ustuni bo'sh bo'lishi mumkin emas")
    private String username;
    @NotNull(message = "password ustuni bo'sh bo'lishi mumkin emas")
    private String password;
    @NotNull(message = "workTask ustuni bo'sh bo'lishi mumkin emas")
    private String workTask;
}
