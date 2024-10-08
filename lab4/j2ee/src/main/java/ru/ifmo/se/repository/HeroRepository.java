package ru.ifmo.se.repository;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import ru.ifmo.se.model.entity.HeroEntity;
import ru.ifmo.se.model.internal.JpaRsqlVisitorParams;
import ru.ifmo.se.rsql.HeroEntityJpaRsqlVisitor;

import java.util.List;

@ApplicationScoped
public class HeroRepository {
    @PersistenceContext(unitName = "PostgresDS")
    private EntityManager em;

    public List<HeroEntity> findHeroes(String rsqlPredicate, int limit, int offset) {
        Predicate predicate = null;

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<HeroEntity> query = criteriaBuilder.createQuery(HeroEntity.class);
        Root<HeroEntity> root = query.from(HeroEntity.class);
        CriteriaQuery<HeroEntity> select = query.select(root);

        if (rsqlPredicate != null && !rsqlPredicate.isEmpty()) {
            JpaRsqlVisitorParams<HeroEntity> jpaRsqlVisitorParams = new JpaRsqlVisitorParams<>(
                    criteriaBuilder,
                    query,
                    root,
                    select
            );

            RSQLParser rsqlParser = new RSQLParser();
            Node rootRsqlNode = rsqlParser.parse(rsqlPredicate);
            predicate = rootRsqlNode.accept(new HeroEntityJpaRsqlVisitor(), jpaRsqlVisitorParams);
        }

        if (predicate != null) {
            select = select.where(predicate);
        }

        TypedQuery<HeroEntity> typedQuery = em.createQuery(select);

        typedQuery.setMaxResults(limit);
        typedQuery.setFirstResult(offset);
        List<HeroEntity> result = typedQuery.getResultList();

        return result;
    }
}
