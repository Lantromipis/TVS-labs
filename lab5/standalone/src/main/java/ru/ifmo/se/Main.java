package ru.ifmo.se;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ru.ifmo.se.repository.EntityManagerFactoryProvider;
import ru.ifmo.se.repository.HeroRepository;
import ru.ifmo.se.rest.HeroResource;
import ru.ifmo.se.service.api.HeroService;
import ru.ifmo.se.service.impl.HeroServiceImpl;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception {
        EntityManagerFactoryProvider entityManagerFactoryProvider = new EntityManagerFactoryProvider(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres"
        );
        HeroRepository heroRepository = new HeroRepository(entityManagerFactoryProvider.getEntityManagerFactory());
        HeroService heroService = new HeroServiceImpl(heroRepository);

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build();
        ResourceConfig config = new ResourceConfig();
        config.register(JacksonJsonProvider.class);
        config.register(new HeroResource(heroService));
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("HTTP server started on port 8080");
        System.in.read();
        server.stop(0);
    }
}
