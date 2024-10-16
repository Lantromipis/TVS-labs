package ru.ifmo.se.command.api;

import java.util.Scanner;

public interface CliCommand {
    void execute(Scanner scanner) throws Exception;

    String getName();

    String getDescription();
}
