package ru.ifmo.se;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.command.impl.*;
import ru.ifmo.se.exception.RestClientException;
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
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, CliCommand> commands = produceCommands(restUrl, objectMapper);
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
                handleException(e, objectMapper);
            }
        }
    }

    private static Map<String, CliCommand> produceCommands(String restUrl, ObjectMapper objectMapper) throws Exception {
        Map<String, CliCommand> commands = new LinkedHashMap<>();

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        objectMapper.setDefaultPrettyPrinter(prettyPrinter);

        Client restClient = ClientBuilder.newClient().register(JacksonJsonProvider.class);
        HeroRestClient heroRestClient = new HeroRestClient(restClient, restUrl);

        ListHeroesCommand listHeroesCommand = new ListHeroesCommand(heroRestClient, objectMapper);
        commands.put(listHeroesCommand.getName(), listHeroesCommand);

        GetHeroCommand getHeroCommand = new GetHeroCommand(heroRestClient, objectMapper);
        commands.put(getHeroCommand.getName(), getHeroCommand);

        AddHeroCommand addHeroCommand = new AddHeroCommand(heroRestClient, objectMapper);
        commands.put(addHeroCommand.getName(), addHeroCommand);

        UpdateHeroCommand updateHeroCommand = new UpdateHeroCommand(heroRestClient, objectMapper);
        commands.put(updateHeroCommand.getName(), updateHeroCommand);

        DeleteHeroCommand deleteHeroCommand = new DeleteHeroCommand(heroRestClient);
        commands.put(deleteHeroCommand.getName(), deleteHeroCommand);

        HelpCommand helpCommand = new HelpCommand(commands);
        commands.put(helpCommand.getName(), helpCommand);

        return commands;
    }

    private static void handleException(Exception e, ObjectMapper objectMapper) {
        if (e instanceof RestClientException restClientException) {
            String errorDtoStr;
            try {
                errorDtoStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(restClientException.getError());
            } catch (Exception ex) {
                errorDtoStr = "null";
            }

            System.out.println("REST server response contains error:\n" + errorDtoStr);
        } else {
            System.out.println("Command failed to execute!!! Message: <" + e.getMessage() + ">\n");
        }
    }
}
