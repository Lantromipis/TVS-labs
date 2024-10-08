package ru.ifmo.se.repository;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.entity.HeroEntity;
import ru.ifmo.se.model.internal.JpaRsqlVisitorParams;
import ru.ifmo.se.rsql.HeroEntityJpaRsqlVisitor;

import java.util.List;

public class HeroRepository {
    private EntityManagerFactory emf;

    public HeroRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<HeroEntity> findHeroes(String rsqlPredicate, int limit, int offset) {
        EntityManager em = emf.createEntityManager();
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
        em.close();

        return result;
    }

    public HeroEntity addhero(HeroEntity hero) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        em.persist(hero);

        entityTransaction.commit();
        em.close();

        return hero;
    }

    public HeroEntity updateHero(HeroEntity hero) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        em.merge(hero);

        entityTransaction.commit();
        em.close();

        return hero;
    }

    public HeroEntity findHero(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        HeroEntity hero = em.find(HeroEntity.class, id);
        em.close();

        return hero;
    }

    public boolean deleteHero(long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        int count = em.createQuery("DELETE FROM HeroEntity WHERE id=:id")
                .setParameter("id", id)
                .executeUpdate();

        entityTransaction.commit();
        em.close();

        return count > 0;
    }
}
