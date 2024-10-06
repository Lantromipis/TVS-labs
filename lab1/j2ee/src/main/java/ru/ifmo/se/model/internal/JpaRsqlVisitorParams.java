package ru.ifmo.se.model.internal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ifmo.se.model.entity.HeroEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JpaRsqlVisitorParams<T> {
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<T> query;
    Root<T> root;
    CriteriaQuery<T> select;
}
