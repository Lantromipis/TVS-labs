package ru.ifmo.se.rsql.field;

public class JpaRsqlDoubleFieldProvider extends AbstractJpaRsqlFledProvider{
    public JpaRsqlDoubleFieldProvider(String fieldName) {
        super(fieldName);
    }

    @Override
    public Comparable getTypedValue(String stringValue) {
        return Double.valueOf(stringValue);
    }
}
