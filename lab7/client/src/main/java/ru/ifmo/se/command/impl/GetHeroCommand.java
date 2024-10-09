package ru.ifmo.se.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ifmo.se.command.CommandUtils;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.soap.HeroDto;
import ru.ifmo.se.soap.HeroListRequestDto;
import ru.ifmo.se.soap.HeroWebService;

import java.util.List;
import java.util.Scanner;

public class GetHeroCommand implements CliCommand {
    private final HeroWebService heroWebService;
    private final ObjectMapper objectMapper;

    public GetHeroCommand(HeroWebService heroWebService, ObjectMapper objectMapper) {
        this.heroWebService = heroWebService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(Scanner scanner) throws Exception {
        long id = CommandUtils.parseLong(
                "Enter id of hero to get as long:",
                "You must enter number.",
                true,
                scanner
        );

        HeroDto result = heroWebService.findHero(id);
        try {
            String resultAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println("Hero updated (server response):\n" + resultAsString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "getHero";
    }

    @Override
    public String getDescription() {
        return "Retrieve hero by its id.";
    }
}
