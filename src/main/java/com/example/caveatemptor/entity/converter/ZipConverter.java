package com.example.caveatemptor.entity.converter;

import com.example.caveatemptor.entity.others.GermanZipCode;
import com.example.caveatemptor.entity.others.SwizzZipCode;
import com.example.caveatemptor.entity.others.ZipCode;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ZipConverter implements AttributeConverter<ZipCode, String> {
    @Override
    public String convertToDatabaseColumn(ZipCode attribute) {
//        System.out.println("convertToDatabaseColumn: " + attribute.getValue());
        return attribute.getValue();
    }


    @Override
    public ZipCode convertToEntityAttribute(String dbData) {
//        System.out.println("convertToEntityAttribute: " + dbData);
        try {
            if (5 == dbData.length()) {
                return new GermanZipCode(dbData);
            } else if (4 == dbData.length()) {
                return new SwizzZipCode(dbData);
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Unsupported zipcode in database: " + dbData);
        }
    }
}
