package ru.ifmo.se;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.command.impl.HelpCommand;
import ru.ifmo.se.command.impl.ListHeroesCommand;
import ru.ifmo.se.soap.HeroDto;
import ru.ifmo.se.soap.HeroListRequestDto;
import ru.ifmo.se.soap.HeroWebService;
import ru.ifmo.se.soap.HeroWebServiceService;

import java.net.URL;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Map<String, CliCommand> commands = produceCommands();
        Scanner sc = new Scanner(System.in);

        String line = null;
        System.out.println("Welcome to Dota 2 Heroes SOAP Service! Enter command to proceed. Use 'help' for help.");
        while (true) {
            line = sc.nextLine();
            if (line.equals("exit")) {
                break;
            }
            CliCommand command = commands.get(line);
            if (command == null) {
                System.out.println("Unknown command: " + line + "\n");
            } else {
                command.execute(sc);
                System.out.println("\n");
            }
        }
    }

    private static Map<String, CliCommand> produceCommands() throws Exception {
        Map<String, CliCommand> commands = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        objectMapper.setDefaultPrettyPrinter(prettyPrinter);

        URL url = new URL("http://localhost:8080/HeroService?wsdl");
        HeroWebServiceService heroWebServiceService = new HeroWebServiceService(url);
        HeroWebService heroWebServiceProxy = heroWebServiceService.getHeroWebServicePort();

        ListHeroesCommand listHeroesCommand = new ListHeroesCommand(heroWebServiceProxy, objectMapper);
        commands.put(listHeroesCommand.getName(), listHeroesCommand);

        HelpCommand helpCommand = new HelpCommand(commands);
        commands.put(helpCommand.getName(), helpCommand);

        return commands;
    }
}
