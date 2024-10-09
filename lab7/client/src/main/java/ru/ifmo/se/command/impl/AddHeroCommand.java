package ru.ifmo.se.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ifmo.se.command.CommandUtils;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.soap.HeroDto;
import ru.ifmo.se.soap.HeroWebService;

import java.util.Scanner;

public class AddHeroCommand implements CliCommand {
    private final HeroWebService heroWebService;
    private final ObjectMapper objectMapper;

    public AddHeroCommand(HeroWebService heroWebService, ObjectMapper objectMapper) {
        this.heroWebService = heroWebService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(Scanner scanner) {
        String heroName;
        while (true) {
            System.out.println("Enter the name of the hero:");
            heroName = scanner.nextLine();
            if (heroName == null || heroName.isEmpty()) {
                System.out.println("You must specify hero name.");
            } else {
                break;
            }
        }

        String mainAttribute;
        while (true) {
            int mainAttributeNum = CommandUtils.parseInt(
                    "Enter the hero main attribute by number STRENGTH (1), AGILITY(2), INTELLIGENCE(3), UNIVERSAL(4) :",
                    "You must enter number.",
                    true,
                    scanner
            );

            switch (mainAttributeNum){
                case 1 -> mainAttribute = "STRENGTH";
                case 2 -> mainAttribute = "AGILITY";
                case 3 -> mainAttribute = "INTELLIGENCE";
                case 4 -> mainAttribute = "UNIVERSAL";
                default -> {
                    System.out.println("You must enter a number in range [1;4].");
                    continue;
                }
            }
            break;
        }

        boolean melee = CommandUtils.parseBoolean(
                "Is your hero melee? (user true or false):",
                "Enter true or false",
                true,
                scanner
        );

        int moveSpeed = CommandUtils.parseInt(
                "Enter hero move speed as integer:",
                "You must enter number.",
                true,
                scanner
        );

        int damage = CommandUtils.parseInt(
                "Enter hero damage as integer:",
                "You must enter number.",
                true,
                scanner
        );

        double armor = CommandUtils.parseDouble(
                "Enter hero armor as double:",
                "You must enter number.",
                true,
                scanner
        );

        HeroDto heroDto = new HeroDto();
        heroDto.setName(heroName);
        heroDto.setMainAttribute(mainAttribute);
        heroDto.setMelee(melee);
        heroDto.setMoveSpeed(moveSpeed);
        heroDto.setDamage(damage);
        heroDto.setArmor(armor);

        HeroDto result = heroWebService.addHero(heroDto);
        try {
            String resultAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println("Hero added (server response):\n" + resultAsString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "addHero";
    }

    @Override
    public String getDescription() {
        return "Add new hero.";
    }
}
