package ru.ifmo.se.rest;

import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.service.api.HeroService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/api/v1/hero")
public class HeroResource {
    private final HeroService heroService;

    public HeroResource(HeroService heroService) {
        this.heroService = heroService;
    }

    @GET
    public List<HeroDto> getHeroes(@QueryParam("rsqlPredicate") String rsqlPredicate,
                                   @QueryParam("limit") Integer limit,
                                   @QueryParam("offset") Integer offset) {
        int resultLimit = 10;
        int resultOffset = 0;

        if (limit != null) {
            resultLimit = limit;
        }

        if (offset != null) {
            resultOffset = offset;
        }

        return heroService.findHeroes(rsqlPredicate, resultOffset, resultLimit);
    }
}
