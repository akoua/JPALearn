package com.example.caveatemptor.entity.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@With
@AllArgsConstructor
public class SwizzZipCode extends ZipCode {
    public SwizzZipCode(String value) {
        super(value);
    }
}
