package com.epam.polosmak.task4.command;

import com.epam.polosmak.task4.command.commandImpl.*;
import com.epam.polosmak.task4.context.ApplicationContext;
import com.epam.polosmak.task4.util.LocaleUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.epam.polosmak.task4.constant.Constants.*;

/**
 * Class init Command impl to model map.
 */
public class CommandController {

    private Map<String, Command> commandMap = new HashMap<>();

    public CommandController(Scanner scanner, ApplicationContext applicationContext) {

        applicationContext.getStoreService().initDeserialization();

        setLocal(scanner);

        applicationContext.getInputStrategy().setTemplate();

        applicationContext.getReflectionInputHandler().setTemplate(applicationContext.getInputStrategy().getInputType());

        commandMap.put(ADD_TO_CART, new AddProductToCartCommand(applicationContext.getCartService(), applicationContext.getStoreService(), scanner));
        commandMap.put(SHOW_CART, new ShowCartCommand(applicationContext.getCartService()));
        commandMap.put(SHOW_TOTAL_PRICE, new TotalPriceCommand(applicationContext.getCartService()));
        commandMap.put(BUY_PRODUCTS, new BuyProductsCommand(applicationContext.getOrderService(), applicationContext.getCartService(), scanner));
        commandMap.put(GET_ORDER_BY_DATE, new OrderByDateCommand(applicationContext.getOrderService(), scanner));
        commandMap.put(GET_ORDER_BY_NEAREST_DATE, new OrderByNearestDateCommand(applicationContext.getOrderService(), scanner));
        commandMap.put(GET_LAST_PRODUCTS, new LastAddedProductsToCartCommand(applicationContext.getCartService().getLastAddedProductsToCart()));
        commandMap.put(PRODUCT_LIST, new ProductListCommand(applicationContext.getStoreService()));
        commandMap.put(ADD_TO_STORE, new AddProductToStoreCommand(applicationContext.getInputStrategy(), applicationContext.getStoreService()));
        commandMap.put(ADD_TO_STORE_REFLECTION, new AddProductToStoreReflectionCommand(applicationContext.getReflectionInputHandler(), applicationContext.getStoreService()));
        commandMap.put(GET_COUNT, new GetCountCommand(applicationContext.getStoreService()));
        commandMap.put(GET_ITEM, new GetItemCommand(applicationContext.getStoreService(), scanner));
        commandMap.put(HELP, new HelpCommand());
        commandMap.put(EXIT, new ExitCommand(applicationContext.getStoreService()));
    }

    private static void setLocal(Scanner scanner) {
        System.out.println("Choose application language:\n0.English(default)\n1.Русский");
        String language = scanner.nextLine();
        LocaleUtil.setLocal(language);
    }

    /**
     * Method fetch controller from commandMap by commandName.
     *
     * @param commandName
     */
    public void handleCommand(String commandName) throws IOException {
        if (commandMap.containsKey(commandName)) {
            Command command = commandMap.get(commandName);

            command.execute();
        } else {
            Command command = commandMap.get(HELP);
            command.execute();
        }
    }
}
