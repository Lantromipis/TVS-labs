package ru.ifmo.se.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Map;

public class EntityManagerFactoryProvider {
    private EntityManagerFactory emf;

    public EntityManagerFactoryProvider(String url, String username, String password) {
        emf = Persistence.createEntityManagerFactory(
                "postgres",
                Map.of(
                        "javax.persistence.jdbc.url", url,
                        "javax.persistence.jdbc.user", username,
                        "javax.persistence.jdbc.password", password
                )
        );
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
