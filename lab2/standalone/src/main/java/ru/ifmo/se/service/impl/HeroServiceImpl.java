package ru.ifmo.se.service.impl;

import ru.ifmo.se.exception.EntityNotFoundByIdException;
import ru.ifmo.se.mapper.HeroMapper;
import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.entity.HeroEntity;
import ru.ifmo.se.model.entity.MainAttribute;
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
        List<HeroEntity> heroEntities = heroRepository.findHeroes(rsqlPredicate, offset, limit);
        return heroEntities.stream().map(HeroMapper::from).toList();
    }

    @Override
    public HeroDto addHero(HeroDto hero) {
        HeroEntity heroEntity = HeroMapper.from(hero);
        heroEntity = heroRepository.addhero(heroEntity);
        return HeroMapper.from(heroEntity);
    }

    @Override
    public HeroDto updateHero(HeroDto hero) {
        HeroEntity existingHero = heroRepository.findHero(hero.getId());
        if (existingHero == null) {
            throw new EntityNotFoundByIdException(String.valueOf(hero.getId()));
        }

        if (hero.getName() != null) {
            existingHero.setName(hero.getName());
        }
        if (hero.getMainAttribute() != null) {
            existingHero.setMainAttribute(HeroMapper.mainAttributeFromString(hero.getMainAttribute()));
        }
        if (hero.getDamage() != null) {
            existingHero.setDamage(hero.getDamage());
        }
        if (hero.getArmor() != null) {
            existingHero.setArmor(hero.getArmor());
        }
        if (hero.getMoveSpeed() != null) {
            existingHero.setMoveSpeed(hero.getMoveSpeed());
        }
        if (hero.getMelee() != null) {
            existingHero.setMelee(hero.getMelee());
        }

        existingHero = heroRepository.updateHero(existingHero);
        return HeroMapper.from(existingHero);
    }

    @Override
    public boolean deleteHero(long id) {
        return heroRepository.deleteHero(id);
    }
}
