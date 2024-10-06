package ru.ifmo.se;

import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.repository.EntityManagerFactoryProvider;
import ru.ifmo.se.repository.HeroRepository;
import ru.ifmo.se.service.api.HeroService;
import ru.ifmo.se.service.impl.HeroServiceImpl;
import ru.ifmo.se.soap.HeroWebService;

import javax.xml.ws.Endpoint;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactoryProvider entityManagerFactoryProvider = new EntityManagerFactoryProvider(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres"
        );
        HeroRepository heroRepository = new HeroRepository(entityManagerFactoryProvider.getEntityManagerFactory());
        HeroService heroService = new HeroServiceImpl(heroRepository);

        Endpoint.publish("http://localhost:8080/HeroService", new HeroWebService(heroService));
    }
}
