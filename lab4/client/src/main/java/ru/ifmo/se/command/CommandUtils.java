package ru.ifmo.se.command;

import java.util.Scanner;

public final class CommandUtils {
    public static int parseInt(String messageToEnter, String errorMessage, Scanner scanner) {
        while (true) {
            System.out.println(messageToEnter);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private CommandUtils() {
    }
}
