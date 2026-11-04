package com.example.demo.dto;

import com.example.demo.entity.Gender;
import com.example.demo.entity.Status;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ClientUpdateRequest {

    @Pattern(regexp = ".*\\S.*")
    private String fullName;

    private Gender gender;

    private Status status;
}
