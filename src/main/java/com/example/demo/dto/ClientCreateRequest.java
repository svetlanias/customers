package com.example.demo.dto;

import com.example.demo.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClientCreateRequest {

    @NotBlank(message = "fullName must not be blank")
    private String fullName;

    @NotNull(message = "gender is required (M or F)")
    private Gender gender;
}
