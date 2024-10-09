package ru.ifmo.se.rsql.field;

import javax.persistence.criteria.Path;
import ru.ifmo.se.model.internal.JpaRsqlVisitorParams;

public interface JpaRsqlFieldProvider {
    String getApplicableFieldName();

    Path getFieldPath(JpaRsqlVisitorParams rsqlVisitorParams);

    Comparable getTypedValue(String stringValue);
}
