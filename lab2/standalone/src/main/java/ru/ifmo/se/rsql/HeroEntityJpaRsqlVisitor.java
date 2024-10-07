package ru.ifmo.se.rsql;

import ru.ifmo.se.model.entity.HeroEntity;
import ru.ifmo.se.rsql.field.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HeroEntityJpaRsqlVisitor extends AbstractJpaRsqlVisitor<HeroEntity> {
    private final static List<JpaRsqlFieldProvider> HERO_FIELDS_PROVIDERS = Arrays.asList(
            new JpaRsqLongFieldProvider("id"),
            new JpaRsqlStringFieldProvider("name"),
            new JpaRsqlMainAttributeFieldProvider("mainAttribute"),
            new JpaRsqlBooleanFieldProvider("melee"),
            new JpaRsqlIntegerFieldProvider("moveSpeed"),
            new JpaRsqlIntegerFieldProvider("damage"),
            new JpaRsqlDoubleFieldProvider("armor")
    );

    private final static Map<String, JpaRsqlFieldProvider> HERO_FIELDS_PROVIDERS_MAP = HERO_FIELDS_PROVIDERS.stream().collect(
            Collectors.toMap(
                    JpaRsqlFieldProvider::getApplicableFieldName,
                    Function.identity()
            )
    );

    public HeroEntityJpaRsqlVisitor() {
        super(HERO_FIELDS_PROVIDERS_MAP);
    }
}
