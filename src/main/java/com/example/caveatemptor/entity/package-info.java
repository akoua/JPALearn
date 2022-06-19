//https://www.baeldung.com/hibernate-identifiers
@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
//        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        strategy = "org.hibernate.id.UUIDGenerator"
//        parameters = {
//                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "JS_SEQUENCE"),
//                @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000")
//        }
)
package com.example.caveatemptor.entity;




