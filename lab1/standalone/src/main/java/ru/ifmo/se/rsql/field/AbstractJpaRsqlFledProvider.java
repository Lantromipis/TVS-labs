package ru.ifmo.se.rsql.field;

import javax.persistence.criteria.Path;
import ru.ifmo.se.model.internal.JpaRsqlVisitorParams;

public abstract class AbstractJpaRsqlFledProvider implements JpaRsqlFieldProvider {
    private final String fieldName;

    public AbstractJpaRsqlFledProvider(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getApplicableFieldName() {
        return fieldName;
    }

    @Override
    public Path getFieldPath(JpaRsqlVisitorParams rsqlVisitorParams) {
        return rsqlVisitorParams.getRoot().get(fieldName);
    }
}
