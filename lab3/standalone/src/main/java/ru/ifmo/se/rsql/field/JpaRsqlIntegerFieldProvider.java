package ru.ifmo.se.rsql.field;

public class JpaRsqlIntegerFieldProvider extends AbstractJpaRsqlFledProvider{
    public JpaRsqlIntegerFieldProvider(String fieldName) {
        super(fieldName);
    }

    @Override
    public Comparable getTypedValue(String stringValue) {
        return Integer.valueOf(stringValue);
    }
}
