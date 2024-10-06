package ru.ifmo.se.soap;

import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.dto.HeroListRequestDto;
import ru.ifmo.se.service.api.HeroService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@ApplicationScoped
@WebService(serviceName = "HeroService")
public class HeroWebService {
    @Inject
    HeroService heroService;

    public HeroWebService() {
    }

    @WebMethod
    public List<HeroDto> listHeroes(HeroListRequestDto requestDto) {
        int limit = 10;
        int offset = 0;

        if (requestDto.getLimit() != null) {
            limit = requestDto.getLimit();
        }

        if (requestDto.getOffset() != null) {
            offset = requestDto.getOffset();
        }

        return heroService.findHeroes(requestDto.getRsqlPredicate(), limit, offset);
    }
}
