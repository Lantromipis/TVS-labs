package ru.ifmo.se;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.command.impl.*;
import ru.ifmo.se.command.impl.juddi.LookupServiceCommand;
import ru.ifmo.se.command.impl.juddi.RegisterBusinessCommand;
import ru.ifmo.se.command.impl.juddi.RegisterServiceCommand;
import ru.ifmo.se.juddi.JuddiClient;
import ru.ifmo.se.soap.*;

import java.net.URL;
import java.util.*;

public class Main {
    public final static String HERO_SERVICE_NAME = "HeroService";
    public final static String TVS_COMPANY_NAME = "TVS";

    public static boolean SOAP_URL_SELECTED = false;
    public static boolean SOAP_COMMANDS_PRODUCED = false;
    public static URL SOAP_URL = null;

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            System.out.println("Usage: java -jar <jar-file-name>.jar <juddi-host> <juddi-port> <juddi-username> <juddi-password>");
            System.exit(1);
        }

        Scanner sc = new Scanner(System.in);

        String juddHost = args[0];
        String juddiPort = args[1];
        String juddiUser = args[2];
        String juddiPassword = args[3];

        Map<String, CliCommand> commands = produceJuddiCommands(juddHost, juddiPort, juddiUser, juddiPassword, sc);
        System.out.println("Welcome to HeroService selection! Enter command to proceed. Use 'help' for help.");

        String line = null;
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

                if (SOAP_URL_SELECTED && !SOAP_COMMANDS_PRODUCED) {
                    System.out.println("Welcome to Dota 2 Heroes SOAP Service! Enter command to proceed. Use 'help' for help.");
                    commands = produceMainCommands(SOAP_URL);
                    SOAP_COMMANDS_PRODUCED = true;
                }

            } catch (Exception e) {
                handleException(e);
            }
        }
    }

    private static Map<String, CliCommand> produceJuddiCommands(String juddHost, String juddiPort, String user, String password, Scanner scanner) throws Exception {
        JuddiClient juddiClient = new JuddiClient(juddHost, Integer.valueOf(juddiPort), user, password);
        Map<String, CliCommand> commands = new LinkedHashMap<>();

        LookupServiceCommand lookupServiceCommand = new LookupServiceCommand(juddiClient);
        commands.put(lookupServiceCommand.getName(), lookupServiceCommand);

        RegisterServiceCommand registerServiceCommand = new RegisterServiceCommand(juddiClient);
        commands.put(registerServiceCommand.getName(), registerServiceCommand);

        RegisterBusinessCommand registerBusinessCommand = new RegisterBusinessCommand(juddiClient);
        commands.put(registerBusinessCommand.getName(), registerBusinessCommand);

        HelpCommand helpCommand = new HelpCommand(commands);
        commands.put(helpCommand.getName(), helpCommand);

        return commands;
    }

    private static Map<String, CliCommand> produceMainCommands(URL soapUrl) {
        Map<String, CliCommand> commands = new LinkedHashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
        prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
        objectMapper.setDefaultPrettyPrinter(prettyPrinter);

        HeroService heroService = new HeroService(soapUrl);
        HeroWebService heroWebServiceProxy = heroService.getHeroWebServicePort();

        ListHeroesCommand listHeroesCommand = new ListHeroesCommand(heroWebServiceProxy, objectMapper);
        commands.put(listHeroesCommand.getName(), listHeroesCommand);

        GetHeroCommand getHeroCommand = new GetHeroCommand(heroWebServiceProxy, objectMapper);
        commands.put(getHeroCommand.getName(), getHeroCommand);

        AddHeroCommand addHeroCommand = new AddHeroCommand(heroWebServiceProxy, objectMapper);
        commands.put(addHeroCommand.getName(), addHeroCommand);

        UpdateHeroCommand updateHeroCommand = new UpdateHeroCommand(heroWebServiceProxy, objectMapper);
        commands.put(updateHeroCommand.getName(), updateHeroCommand);

        DeleteHeroCommand deleteHeroCommand = new DeleteHeroCommand(heroWebServiceProxy);
        commands.put(deleteHeroCommand.getName(), deleteHeroCommand);

        HelpCommand helpCommand = new HelpCommand(commands);
        commands.put(helpCommand.getName(), helpCommand);

        return commands;
    }

    private static void handleException(Exception e) {
        e.printStackTrace();
        if (e instanceof EntityNotFoundByIdException entityNotFoundByIdException) {
            System.out.println(
                    "Entity with name '" +
                            entityNotFoundByIdException.getFaultInfo().getEntityName() +
                            "' and id '" +
                            entityNotFoundByIdException.getFaultInfo().getEntityId() +
                            "' is not found!"
            );
        } else if (e instanceof UnsupportedRsqlOperatorException unsupportedRsqlOperatorException) {
            System.out.println(
                    "RSQL operator with symbol " +
                            unsupportedRsqlOperatorException.getFaultInfo().getOperatorSymbol() +
                            " is not supported!"
            );
        } else if (e instanceof UnknownEntityFieldException unknownEntityFieldException) {
            System.out.println(
                    "Entity with name '" +
                            unknownEntityFieldException.getFaultInfo().getEntityName() +
                            "' has not field with name '" +
                            unknownEntityFieldException.getFaultInfo().getFieldName() +
                            "'!"
            );
        } else {
            System.out.println("Command failed to execute!!! Message: <" + e.getMessage() + ">\n");
        }
    }
}
