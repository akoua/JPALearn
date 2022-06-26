package com.example.caveatemptor.entity.others;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@Data
@With
@AllArgsConstructor
public class GermanZipCode extends ZipCode {
    public GermanZipCode(String value) {
        super(value);
    }
}
