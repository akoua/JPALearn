package com.example.caveatemptor.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@With
@AllArgsConstructor
@NoArgsConstructor
public class Auditing implements Serializable {

    @CreationTimestamp
    private LocalDate createOn;
    @UpdateTimestamp
    private LocalDateTime lastModified;
    private String createBy;
    private String modifiedBy;
}
