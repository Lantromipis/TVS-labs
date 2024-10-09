package ru.ifmo.se.command.impl.juddi;

import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingDetail;
import ru.ifmo.se.Main;
import ru.ifmo.se.command.CommandUtils;
import ru.ifmo.se.command.api.CliCommand;
import ru.ifmo.se.juddi.JuddiClient;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class RegisterServiceCommand implements CliCommand {
    private final JuddiClient juddiClient;

    public RegisterServiceCommand(JuddiClient juddiClient) {
        this.juddiClient = juddiClient;
    }

    @Override
    public void execute(Scanner scanner) throws Exception {
        String businessName = CommandUtils.enterString(
                "Enter business name in which you will register service",
                "You must enter business name!",
                true,
                scanner
        );

        String businessKey = juddiClient.findBusinessInfo(businessName);
        if (businessKey == null) {
            System.out.println("Business you specified does not exist! First create it using createBusiness command!");
            return;
        }

        String serviceKey = juddiClient.findServiceInfo(businessKey, Main.HERO_SERVICE_NAME);
        String baseUrl = CommandUtils.enterString(
                "Enter HeroService base URL (without name and ?wsdl)",
                "You must enter service base URL!",
                true,
                scanner
        );
        URL serviceUrl = new URL(baseUrl);

        AuthToken authToken = juddiClient.provideAuthToken();
        if (serviceKey == null) {
            serviceKey = juddiClient.registerService(businessKey, Main.HERO_SERVICE_NAME, authToken);
            System.out.println("Registered HeroService as service with key " + serviceKey);
        } else {
            System.out.println("Service for HeroService already exists! Will check binding to url...");
        }

        BindingDetail bindingDetail = juddiClient.findServiceBindingInfo(serviceKey);
        if (bindingDetail != null) {
            List<String> urls = JuddiClient.getUrlsFromBinding(bindingDetail);
            if (urls.contains(baseUrl)) {
                System.out.println("Binding for HeroService with the same URL already exist!");
                juddiClient.discardAuthToken(authToken);
                return;
            } else {
                System.out.println("Binding for HeroService exists, but it hase different binding. Will register a new binding.");
            }
        } else {
            System.out.println("No bindings for HeroService exist! Will register a new binding.");
        }

        bindingDetail = juddiClient.bindService(serviceKey, serviceUrl, authToken);
        System.out.println("Successfully created binding for HeroService");
    }

    @Override
    public String getName() {
        return "registerService";
    }

    @Override
    public String getDescription() {
        return "Register HeroService in JUDDI";
    }
}
