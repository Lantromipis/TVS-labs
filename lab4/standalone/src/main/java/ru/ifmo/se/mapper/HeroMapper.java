package ru.ifmo.se.mapper;

import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.entity.HeroEntity;

public final class HeroMapper {
    public static HeroDto from(HeroEntity entity) {
        return HeroDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .mainAttribute(entity.getMainAttribute().name())
                .melee(entity.isMelee())
                .armor(entity.getArmor())
                .damage(entity.getDamage())
                .moveSpeed(entity.getMoveSpeed())
                .build();
    }

    private HeroMapper() {
    }
}
