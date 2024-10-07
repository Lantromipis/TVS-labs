package ru.ifmo.se.service.api;

import ru.ifmo.se.model.dto.HeroDto;

import java.util.List;

public interface HeroService {
    List<HeroDto> findHeroes(String rsqlPredicate, int offset, int limit);

    HeroDto addHero(HeroDto hero);

    HeroDto updateHero(HeroDto hero);

    boolean deleteHero(long id);
}
