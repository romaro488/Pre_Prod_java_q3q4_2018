package com.epam.polosmak.task9.webCommand;

import com.epam.polosmak.task4.service.StoreService;
import com.epam.polosmak.task9.webCommand.http.GetCountHttpCommand;
import com.epam.polosmak.task9.webCommand.http.GetItemHttpCommand;
import com.epam.polosmak.task9.webCommand.tcp.GetCountTCPCommand;
import com.epam.polosmak.task9.webCommand.tcp.GetItemTCPCommand;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.polosmak.task4.constant.Constants.*;

public class WebCommandManager implements WebCommand {

    private static final String WRONG_COMMAND = "wrong command";

    private HashMap<String, WebCommand> commandMap;

    public WebCommandManager(StoreService storeService) {
        commandMap = new HashMap<>();
        commandMap.put(GET_COUNT, new GetCountTCPCommand(storeService));
        commandMap.put(GET_ITEM, new GetItemTCPCommand(storeService));
        commandMap.put(GET_COUNT_HTTP, new GetCountHttpCommand(storeService));
        commandMap.put(GET_ITEM_HTTP, new GetItemHttpCommand(storeService));
    }

    @Override
    public String getCommand(String request) {
        String[] requestWithEquals = request.split("=");
        String[] requestWithEqualsHttp = parseGetQuery(request).split("=");
        if (commandMap.containsKey(request)) {
            return commandMap.get(request).getCommand(request);
        } else if (commandMap.containsKey(requestWithEquals[0])) {
            return commandMap.get(requestWithEquals[0].trim())
                    .getCommand(requestWithEquals[1].trim());
        } else if (commandMap.containsKey(parseGetQuery(request))) {
            return commandMap.get(parseGetQuery(request))
                    .getCommand(parseGetQuery(request));
        } else if (commandMap.containsKey(requestWithEqualsHttp[0].trim())) {
            return commandMap.get(requestWithEqualsHttp[0].trim())
                    .getCommand(requestWithEqualsHttp[1].trim());

        } else {
            return WRONG_COMMAND;
        }
    }


    private String parseGetQuery(String req) {
        Pattern pattern = Pattern.compile("(/[a-z0-9/?_= a-z0-9]+)");
        Matcher matcher = pattern.matcher(req);
        if (matcher.find()) {
            return matcher.group().trim();
        }
        return req;
    }


}
