package ru.ifmo.se.restclient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class HeroRestClient {
    private final Client client;
    private final String baseUrl;

    public HeroRestClient(Client client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    public List<HeroDto> getHeroes(String rsqlPredicate, Integer limit, Integer offset) {
        WebTarget webTarget = client.target(baseUrl);

        webTarget = webTarget.queryParam("rsqlPredicate", rsqlPredicate);
        webTarget = webTarget.queryParam("limit", limit);
        webTarget = webTarget.queryParam("offset", offset);

        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

        return response.readEntity(new GenericType<List<HeroDto>>() {
        });
    }

    public HeroDto getHero(long id) {
        WebTarget webTarget = client.target(baseUrl + "/" + id);

        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

        return response.readEntity(HeroDto.class);
    }

    public HeroDto createHero(HeroDto hero) {
        WebTarget webTarget = client.target(baseUrl);

        Response response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(hero, MediaType.APPLICATION_JSON));
        return response.readEntity(HeroDto.class);
    }

    public HeroDto updateHero(long id, HeroDto hero) {
        WebTarget webTarget = client.target(baseUrl + "/" + id);

        Response response = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(hero, MediaType.APPLICATION_JSON));
        return response.readEntity(HeroDto.class);
    }

    public boolean deleteHero(long id) {
        WebTarget webTarget = client.target(baseUrl + "/" + id);

        Response response = webTarget.request(MediaType.APPLICATION_JSON).delete();

        return response.getStatus() == 200;
    }

}
