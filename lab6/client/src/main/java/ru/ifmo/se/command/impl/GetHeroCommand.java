package ru.ifmo.se.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ifmo.se.command.CommandUtils;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.restclient.HeroDto;
import ru.ifmo.se.restclient.HeroRestClient;

import java.util.Scanner;

public class GetHeroCommand implements CliCommand {
    private final HeroRestClient heroRestClient;
    private final ObjectMapper objectMapper;

    public GetHeroCommand(HeroRestClient heroRestClient, ObjectMapper objectMapper) {
        this.heroRestClient = heroRestClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(Scanner scanner) {
        long id = CommandUtils.parseLong(
                "Enter id of hero to get as long:",
                "You must enter number.",
                true,
                scanner
        );

        HeroDto result = heroRestClient.getHero(id);
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
