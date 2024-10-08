package ru.ifmo.se.command.impl;

import ru.ifmo.se.command.CommandUtils;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.restclient.HeroRestClient;

import java.util.Scanner;

public class DeleteHeroCommand implements CliCommand {
    private final HeroRestClient heroRestClient;

    public DeleteHeroCommand(HeroRestClient heroRestClient) {
        this.heroRestClient = heroRestClient;
    }

    @Override
    public void execute(Scanner scanner) {
        long id = CommandUtils.parseLong(
                "Enter id of hero to be deleted as long:",
                "You must enter number.",
                true,
                scanner
        );

        boolean success = heroRestClient.deleteHero(id);
        if (success) {
            System.out.println("Hero with id " + id + " deleted successfully.");
        } else {
            System.out.println("Failed to delete hero with id " + id + ". Does this hero exist?");
        }
    }

    @Override
    public String getName() {
        return "deleteHero";
    }

    @Override
    public String getDescription() {
        return "DElite hero by id.";
    }
}
