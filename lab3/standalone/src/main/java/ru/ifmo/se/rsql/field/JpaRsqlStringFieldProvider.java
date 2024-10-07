package ru.ifmo.se.rsql.field;

public class JpaRsqlStringFieldProvider extends AbstractJpaRsqlFledProvider{
    public JpaRsqlStringFieldProvider(String fieldName) {
        super(fieldName);
    }

    @Override
    public Comparable getTypedValue(String stringValue) {
        return stringValue;
    }
}
