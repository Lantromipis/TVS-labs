package ru.ifmo.se.rsql.field;

public class JpaRsqlBooleanFieldProvider extends AbstractJpaRsqlFledProvider{
    public JpaRsqlBooleanFieldProvider(String fieldName) {
        super(fieldName);
    }

    @Override
    public Comparable getTypedValue(String stringValue) {
        return Boolean.valueOf(stringValue);
    }
}
