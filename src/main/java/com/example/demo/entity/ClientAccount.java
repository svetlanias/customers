package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, columnDefinition = "text")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private Status status;

    @Column(name = "create_dttm", nullable = false, updatable = false)
    private OffsetDateTime createDttm;

    @Column(name = "modify_dttm", nullable = false)
    private OffsetDateTime modifyDttm;

    @PrePersist
    void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        createDttm = now;
        modifyDttm = now;
        if (status == null) {
            status = Status.ACTIVE;
        }
    }

    @PreUpdate
    void onUpdate() {
        modifyDttm = OffsetDateTime.now();
    }
}