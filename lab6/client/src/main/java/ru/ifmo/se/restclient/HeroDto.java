package ru.ifmo.se.restclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroDto {
    private Long id;
    private String name;
    private String mainAttribute;
    private Boolean melee;
    private Integer moveSpeed;
    private Integer damage;
    private Double armor;
}
