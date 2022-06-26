package com.example.caveatemptor.entity.customizer;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * Permit to add prepend to all table name
 */
public class CustomTableName extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return new Identifier("ce_" + name.getText(), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
//        System.out.println(">>> toPhysicalColumnName " + name.getText());

        return super.toPhysicalColumnName(name, context);
    }
}
