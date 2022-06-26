/**
 * Cf. https://www.baeldung.com/hibernate-identifiers
 * Cf. https://thorben-janssen.com/generate-uuids-primary-keys-hibernate/
 */
@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "org.hibernate.id.UUIDGenerator"
)
package com.example.caveatemptor.entity;




