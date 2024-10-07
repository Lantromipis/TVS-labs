package ru.ifmo.se.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ifmo.se.command.CommandUtils;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.soap.HeroDto;
import ru.ifmo.se.soap.HeroWebService;

import java.util.Scanner;

public class UpdateHeroCommand implements CliCommand {
    private final HeroWebService heroWebService;
    private final ObjectMapper objectMapper;

    public UpdateHeroCommand(HeroWebService heroWebService, ObjectMapper objectMapper) {
        this.heroWebService = heroWebService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(Scanner scanner) throws Exception  {
        long id = CommandUtils.parseLong(
                "Enter id of hero to be updated as long:",
                "You must enter number.",
                true,
                scanner
        );

        System.out.println("Enter new hero name:");
        String heroName = scanner.nextLine();
        if (heroName.isEmpty()) {
            heroName = null;
        }

        String mainAttribute = null;
        while (true) {
            Integer mainAttributeNum = CommandUtils.parseInt(
                    "Enter the hero main attribute by number STRENGTH (1), AGILITY(2), INTELLIGENCE(3), UNIVERSAL(4) :",
                    "You must enter number.",
                    false,
                    scanner
            );

            if (mainAttributeNum == null) {
                break;
            }

            switch (mainAttributeNum) {
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

        Boolean melee = CommandUtils.parseBoolean(
                "Is your hero melee? (user true or false):",
                "Enter true or false",
                false,
                scanner
        );

        Integer moveSpeed = CommandUtils.parseInt(
                "Enter hero move speed as integer:",
                "You must enter number.",
                false,
                scanner
        );

        Integer damage = CommandUtils.parseInt(
                "Enter hero damage as integer:",
                "You must enter number.",
                false,
                scanner
        );

        Double armor = CommandUtils.parseDouble(
                "Enter hero armor as double:",
                "You must enter number.",
                false,
                scanner
        );

        HeroDto heroDto = new HeroDto();
        heroDto.setName(heroName);
        heroDto.setMainAttribute(mainAttribute);
        heroDto.setMelee(melee);
        heroDto.setMoveSpeed(moveSpeed);
        heroDto.setDamage(damage);
        heroDto.setArmor(armor);

        HeroDto result = heroWebService.updateHero(id, heroDto);
        try {
            String resultAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println("Hero updated (server response):\n" + resultAsString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "updateHero";
    }

    @Override
    public String getDescription() {
        return "Update existing hero.";
    }
}
