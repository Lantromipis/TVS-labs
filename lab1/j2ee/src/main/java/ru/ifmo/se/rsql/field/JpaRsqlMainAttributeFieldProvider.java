package ru.ifmo.se.rsql.field;

import ru.ifmo.se.model.entity.MainAttribute;

public class JpaRsqlMainAttributeFieldProvider extends AbstractJpaRsqlFledProvider{
    public JpaRsqlMainAttributeFieldProvider(String fieldName) {
        super(fieldName);
    }

    @Override
    public Comparable getTypedValue(String stringValue) {
        return MainAttribute.valueOf(stringValue);
    }
}
