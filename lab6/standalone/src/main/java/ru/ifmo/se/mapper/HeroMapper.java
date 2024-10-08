package ru.ifmo.se.mapper;

import ru.ifmo.se.model.dto.HeroDto;
import ru.ifmo.se.model.entity.HeroEntity;
import ru.ifmo.se.model.entity.MainAttribute;

public final class HeroMapper {
    public static HeroDto from(HeroEntity entity) {
        return HeroDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .mainAttribute(entity.getMainAttribute().name())
                .melee(entity.getMelee())
                .armor(entity.getArmor())
                .damage(entity.getDamage())
                .moveSpeed(entity.getMoveSpeed())
                .build();
    }

    public static HeroEntity from(HeroDto dto) {
        return HeroEntity
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .mainAttribute(mainAttributeFromString(dto.getMainAttribute()))
                .melee(dto.getMelee())
                .armor(dto.getArmor())
                .damage(dto.getDamage())
                .moveSpeed(dto.getMoveSpeed())
                .build();
    }

    public static MainAttribute mainAttributeFromString(String str) {
        return MainAttribute.valueOf(str);
    }

    private HeroMapper() {
    }
}
