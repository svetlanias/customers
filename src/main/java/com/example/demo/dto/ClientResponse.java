package com.example.demo.dto;

import com.example.demo.entity.Gender;
import com.example.demo.entity.Status;
import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;

@Value
@Builder
public class ClientResponse {
    Long id;
    String fullName;
    Gender gender;
    Status status;
    OffsetDateTime createDttm;
    OffsetDateTime modifyDttm;
}
