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
    private long id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "main_attribute")
    private MainAttribute mainAttribute;
    @Column(name = "melee")
    private boolean melee;
    @Column(name = "move_speed")
    private int moveSpeed;
    @Column(name = "damage")
    private int damage;
    @Column(name = "armor")
    private double armor;
}
