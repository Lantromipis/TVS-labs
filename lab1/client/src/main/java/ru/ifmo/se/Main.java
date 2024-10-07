package ru.ifmo.se;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.command.impl.HelpCommand;
import ru.ifmo.se.command.impl.ListHeroesCommand;
import ru.ifmo.se.soap.HeroService;
import ru.ifmo.se.soap.HeroWebService;

import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java -jar <jar-file-name>.jar <soap-service-url>");
            System.exit(1);
        }

        String soapUrl = args[0];

        Map<String, CliCommand> commands = produceCommands(soapUrl);
        Scanner sc = new Scanner(System.in);

        String line = null;
        System.out.println("Welcome to Dota 2 Heroes SOAP Service! Enter command to proceed. Use 'help' for help.");
        while (true) {
            line = sc.nextLine();
            if (line.equals("exit")) {
                break;
            }
            try {
                CliCommand command = commands.get(line);
                if (command == null) {
                    System.out.println("Unknown command: " + line + "\n");
                } else {
                    command.execute(sc);
                    System.out.println("\n");
                }
            } catch (Exception e) {
                System.out.println("Command failed to execute!!! Message: <" + e.getMessage() + ">\n");
            }
        }
    }

    private static Map<String, CliCommand> produceCommands(String soapUrl) throws Exception {
        Map<String, CliCommand> commands = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        objectMapper.setDefaultPrettyPrinter(prettyPrinter);

        URL url = new URL(soapUrl);
        HeroService heroService = new HeroService(url);
        HeroWebService heroWebServiceProxy = heroService.getHeroWebServicePort();

        ListHeroesCommand listHeroesCommand = new ListHeroesCommand(heroWebServiceProxy, objectMapper);
        commands.put(listHeroesCommand.getName(), listHeroesCommand);

        HelpCommand helpCommand = new HelpCommand(commands);
        commands.put(helpCommand.getName(), helpCommand);

        return commands;
    }
}
