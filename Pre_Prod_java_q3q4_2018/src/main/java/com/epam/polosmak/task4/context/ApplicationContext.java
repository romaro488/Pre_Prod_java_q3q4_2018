package com.epam.polosmak.task4.context;

import com.epam.polosmak.task4.inputHandler.InputStrategy;
import com.epam.polosmak.task4.reflection.reflectionHandler.ReflectionInputHandler;
import com.epam.polosmak.task4.repository.CartRepository;
import com.epam.polosmak.task4.repository.OrderRepository;
import com.epam.polosmak.task4.repository.ProductsRepository;
import com.epam.polosmak.task4.repository.impl.CartRepositoryImpl;
import com.epam.polosmak.task4.repository.impl.LastAddedProductsToCart;
import com.epam.polosmak.task4.repository.impl.OrderRepositoryImpl;
import com.epam.polosmak.task4.repository.impl.ProductsRepositoryImpl;
import com.epam.polosmak.task4.service.impl.CartServiceImpl;
import com.epam.polosmak.task4.service.impl.OrderServiceImpl;
import com.epam.polosmak.task4.service.impl.StoreServiceImpl;
import com.epam.polosmak.task9.handler.ServerHandler;
import com.epam.polosmak.task9.webCommand.WebCommand;
import com.epam.polosmak.task9.webCommand.WebCommandManager;

import static com.epam.polosmak.task4.constant.Constants.PORT_HTTP;
import static com.epam.polosmak.task4.constant.Constants.PORT_TCP;

public class ApplicationContext {

    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private ProductsRepository productsRepository;
    private LastAddedProductsToCart lastAddedProductsToCart;
    private CartServiceImpl cartService;
    private OrderServiceImpl orderService;
    private StoreServiceImpl storeService;
    private InputStrategy inputStrategy;
    private ReflectionInputHandler reflectionInputHandler;
    private WebCommand webCommand;
    private ServerHandler httpServer;
    private ServerHandler tcpServer;
    private WebCommandManager webCommandManager;


    public ApplicationContext() {
        init();
    }

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public ProductsRepository getProductsRepository() {
        return productsRepository;
    }

    public CartServiceImpl getCartService() {
        return cartService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }

    public StoreServiceImpl getStoreService() {
        return storeService;
    }

    public InputStrategy getInputStrategy() {
        return inputStrategy;
    }

    public ReflectionInputHandler getReflectionInputHandler() {
        return reflectionInputHandler;
    }

    public WebCommandManager getWebCommandManager() {
        return webCommandManager;
    }

    private void init() {
        CartRepository cartRepository = new CartRepositoryImpl();
        ProductsRepository productsRepository = new ProductsRepositoryImpl();
        LastAddedProductsToCart lastAddedProductsToCart = new LastAddedProductsToCart();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        InputStrategy inputStrategy = new InputStrategy();
        ReflectionInputHandler reflectionInputHandler = new ReflectionInputHandler();
        this.cartService = new CartServiceImpl(lastAddedProductsToCart, cartRepository);
        this.orderService = new OrderServiceImpl(orderRepository);
        this.storeService = new StoreServiceImpl(productsRepository);
        this.inputStrategy = inputStrategy;
        this.reflectionInputHandler = reflectionInputHandler;
        webCommand = new WebCommandManager(storeService);
        tcpServer = new ServerHandler(PORT_TCP, webCommand);
        httpServer = new ServerHandler(PORT_HTTP, webCommand);
        tcpServer.start();
        httpServer.start();
    }
}
