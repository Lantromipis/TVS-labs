package ru.ifmo.se.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hero")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "main_attribute")
    private MainAttribute mainAttribute;
    @Column(name = "melee")
    private Boolean melee;
    @Column(name = "move_speed")
    private Integer moveSpeed;
    @Column(name = "damage")
    private Integer damage;
    @Column(name = "armor")
    private Double armor;
}
