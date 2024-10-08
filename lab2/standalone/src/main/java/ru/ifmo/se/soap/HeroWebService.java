package ru.ifmo.se.soap;

import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.dto.HeroListRequestDto;
import ru.ifmo.se.service.api.HeroService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "HeroService")
public class HeroWebService {
    private final HeroService heroService;

    public HeroWebService(HeroService heroService) {
        this.heroService = heroService;
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

        return heroService.findHeroes(requestDto.getRsqlPredicate(), offset, limit);
    }

    @WebMethod
    public HeroDto findHero(long id) {
        return heroService.findHero(id);
    }

    @WebMethod
    public HeroDto addHero(HeroDto heroDto) {
        return heroService.addHero(heroDto);
    }

    @WebMethod
    public HeroDto updateHero(long id, HeroDto heroDto) {
        heroDto.setId(id);
        return heroService.updateHero(heroDto);
    }

    @WebMethod
    public boolean deleteHero(long id) {
        return heroService.deleteHero(id);
    }
}
