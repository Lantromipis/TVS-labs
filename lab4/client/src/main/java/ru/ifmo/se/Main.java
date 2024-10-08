package ru.ifmo.se;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.command.impl.HelpCommand;
import ru.ifmo.se.command.impl.ListHeroesCommand;
import ru.ifmo.se.restclient.HeroRestClient;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java -jar <jar-file-name>.jar <soap-service-url>");
            System.exit(1);
        }

        String restUrl = args[0];

        Map<String, CliCommand> commands = produceCommands(restUrl);
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

    private static Map<String, CliCommand> produceCommands(String restUrl) throws Exception {
        Map<String, CliCommand> commands = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        objectMapper.setDefaultPrettyPrinter(prettyPrinter);

        Client restClient = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        HeroRestClient heroRestClient = new HeroRestClient(restClient, restUrl);

        ListHeroesCommand listHeroesCommand = new ListHeroesCommand(heroRestClient, objectMapper);
        commands.put(listHeroesCommand.getName(), listHeroesCommand);

        HelpCommand helpCommand = new HelpCommand(commands);
        commands.put(helpCommand.getName(), helpCommand);

        return commands;
    }
}
