package ru.ifmo.se.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroListRequestDto {
    private String rsqlPredicate;
    private Integer offset;
    private Integer limit;
}
