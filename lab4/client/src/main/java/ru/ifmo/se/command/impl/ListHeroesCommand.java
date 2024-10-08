package ru.ifmo.se.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ifmo.se.command.CommandUtils;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.restclient.HeroDto;
import ru.ifmo.se.restclient.HeroRestClient;

import java.util.List;
import java.util.Scanner;

public class ListHeroesCommand implements CliCommand {
    private final HeroRestClient heroRestClient;
    private final ObjectMapper objectMapper;

    public ListHeroesCommand(HeroRestClient heroRestClient, ObjectMapper objectMapper) {
        this.heroRestClient = heroRestClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(Scanner scanner) {
        System.out.println("Enter RSQL predicate for filtration (empty if not filtration required): ");
        String rsqlPredicate = scanner.nextLine();

        int offset = CommandUtils.parseInt(
                "Enter offset for pagination: ",
                "Offset must be integer",
                scanner
        );

        int limit = CommandUtils.parseInt(
                "Enter limit for pagination: ",
                "Limit must be integer",
                scanner
        );


        List<HeroDto> result = heroRestClient.getHeroes(rsqlPredicate, limit, offset);
        try {
            String resultAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
            System.out.println("Server returned:\n" + resultAsString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName() {
        return "listHeroes";
    }

    @Override
    public String getDescription() {
        return "List available heroes using RSQL predicate with limit and offset.";
    }
}
