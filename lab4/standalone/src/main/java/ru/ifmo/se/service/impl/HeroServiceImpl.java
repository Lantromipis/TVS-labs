package ru.ifmo.se.service.impl;

import ru.ifmo.se.mapper.HeroMapper;
import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.entity.HeroEntity;
import ru.ifmo.se.repository.HeroRepository;
import ru.ifmo.se.service.api.HeroService;

import java.util.List;

public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;

    public HeroServiceImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public List<HeroDto> findHeroes(String rsqlPredicate, int offset, int limit) {
        List<HeroEntity> heroEntities = heroRepository.findHeroes(rsqlPredicate, limit, offset);
        return heroEntities.stream().map(HeroMapper::from).toList();
    }
}
