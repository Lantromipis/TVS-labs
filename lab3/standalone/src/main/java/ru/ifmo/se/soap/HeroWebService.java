package ru.ifmo.se.soap;

import ru.ifmo.se.exception.EntityNotFoundByIdException;
import ru.ifmo.se.exception.UnknownEntityFieldException;
import ru.ifmo.se.exception.UnsupportedRsqlOperatorException;
import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.dto.HeroListRequestDto;
import ru.ifmo.se.service.api.HeroService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.lang.model.UnknownEntityException;
import java.util.List;

@WebService(serviceName = "HeroService")
public class HeroWebService {
    private final HeroService heroService;

    public HeroWebService(HeroService heroService) {
        this.heroService = heroService;
    }

    @WebMethod
    public List<HeroDto> listHeroes(HeroListRequestDto requestDto) throws UnknownEntityFieldException, UnsupportedRsqlOperatorException {
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

    @WebMethod
    public HeroDto findHero(long id) throws EntityNotFoundByIdException {
        return heroService.findHero(id);
    }

    @WebMethod
    public HeroDto addHero(HeroDto heroDto) {
        return heroService.addHero(heroDto);
    }

    @WebMethod
    public HeroDto updateHero(long id, HeroDto heroDto) throws EntityNotFoundByIdException {
        heroDto.setId(id);
        return heroService.updateHero(heroDto);
    }

    @WebMethod
    public boolean deleteHero(long id) throws EntityNotFoundByIdException {
        return heroService.deleteHero(id);
    }
}
