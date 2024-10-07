package ru.ifmo.se.command;

import java.util.Scanner;
import java.util.function.Function;

public final class CommandUtils {
    public static Long parseLong(String messageToEnter, String errorMessage, boolean required, Scanner scanner) {
        return parse(messageToEnter, errorMessage, scanner, required, Long::valueOf);
    }

    public static Integer parseInt(String messageToEnter, String errorMessage, boolean required, Scanner scanner) {
        return parse(messageToEnter, errorMessage, scanner, required, Integer::valueOf);
    }

    public static Double parseDouble(String messageToEnter, String errorMessage, boolean required, Scanner scanner) {
        return parse(messageToEnter, errorMessage, scanner, required, Double::valueOf);
    }

    public static Boolean parseBoolean(String messageToEnter, String errorMessage, boolean required, Scanner scanner) {
        return parse(
                messageToEnter,
                errorMessage,
                scanner,
                required,
                s -> {
                    if ("true".equalsIgnoreCase(s)) {
                        return true;
                    } else if ("false".equalsIgnoreCase(s)) {
                        return false;
                    } else {
                        throw new NumberFormatException("Expected true or false.");
                    }
                }
        );
    }

    private static <T> T parse(String messageToEnter, String errorMessage, Scanner scanner, boolean required, Function<String, T> parser) {
        while (true) {
            System.out.println(messageToEnter);
            String input = scanner.nextLine();
            if (input == null || input.isEmpty() && !required) {
                return null;
            }
            try {
                return parser.apply(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    private CommandUtils() {
    }
}
