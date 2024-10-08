package ru.ifmo.se.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroDto {
    private long id;
    private String name;
    private String mainAttribute;
    private boolean melee;
    private int moveSpeed;
    private int damage;
    private double armor;
}
