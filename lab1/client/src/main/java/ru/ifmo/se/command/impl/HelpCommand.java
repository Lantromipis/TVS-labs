package ru.ifmo.se.command.impl;

import ru.ifmo.se.command.api.CliCommand;

import java.util.Map;
import java.util.Scanner;

public class HelpCommand implements CliCommand {
    private final Map<String, CliCommand> commandMap;

    public HelpCommand(Map<String, CliCommand> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public void execute(Scanner scanner) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are all available commands: \n");

        for (Map.Entry<String, CliCommand> entry : commandMap.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(" : ")
                    .append(entry.getValue().getDescription())
                    .append("\n");
        }

        stringBuilder.append("exit : Exit application");

        System.out.println(stringBuilder);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "Show available commands with description.";
    }
}
