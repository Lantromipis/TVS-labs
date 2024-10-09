package ru.ifmo.se.service.api;

import ru.ifmo.se.exception.EntityNotFoundByIdException;
import ru.ifmo.se.exception.UnknownEntityFieldException;
import ru.ifmo.se.exception.UnsupportedRsqlOperatorException;
import ru.ifmo.se.model.dto.HeroDto;

import java.util.List;

public interface HeroService {
    List<HeroDto> findHeroes(String rsqlPredicate, int offset, int limit) throws UnsupportedRsqlOperatorException, UnknownEntityFieldException;

    HeroDto findHero(long id) throws EntityNotFoundByIdException;

    HeroDto addHero(HeroDto hero);

    HeroDto updateHero(HeroDto hero) throws EntityNotFoundByIdException;

    boolean deleteHero(long id);
}
