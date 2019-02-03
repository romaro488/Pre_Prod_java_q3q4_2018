package com.epam.polosmak.web.listener;

import com.epam.polosmak.dao.InfoOrderedItemDAO;
import com.epam.polosmak.dao.ItemDAO;
import com.epam.polosmak.dao.OrderDAO;
import com.epam.polosmak.dao.UserDAO;
import com.epam.polosmak.dao.impl.InfoOrderedItemDAOImpl;
import com.epam.polosmak.dao.impl.ItemDAOImpl;
import com.epam.polosmak.dao.impl.MySQLUserDAO;
import com.epam.polosmak.dao.impl.OrderDAOImpl;
import com.epam.polosmak.dao.tranaction.TransactionManager;
import com.epam.polosmak.entity.Category;
import com.epam.polosmak.entity.Manufacturer;
import com.epam.polosmak.entity.Product;
import com.epam.polosmak.entity.User;
import com.epam.polosmak.repository.Repository;
import com.epam.polosmak.repository.mysql.MySqlCategoryRepository;
import com.epam.polosmak.repository.mysql.MySqlManufacturerRepository;
import com.epam.polosmak.repository.mysql.MySqlProductRepository;
import com.epam.polosmak.service.*;
import com.epam.polosmak.service.avatar.AvatarServiceImpl;
import com.epam.polosmak.service.captcha.RandomNumberCaptchaService;
import com.epam.polosmak.service.captcha.handler.*;
import com.epam.polosmak.service.dbService.*;
import com.epam.polosmak.validator.*;
import com.epam.polosmak.web.bean.*;
import com.epam.polosmak.web.exctractor.*;
import com.github.cage.Cage;
import com.github.cage.GCage;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ContextListener.class);

    private static final String LOG_PROPERTIES = "log4j.properties";

    private static final String USER_SERVICE = "userService";
    private static final String PRODUCT_SERVICE = "productService";
    private static final String CATEGORY_SERVICE = "categoryService";
    private static final String MANUFACTURER_SERVICE = "manufacturerService";
    private static final String AVATAR_SERVICE = "avatarService";
    private static final String ITEM_SERVICE = "itemService";
    private static final String ORDER_SERVICE = "orderService";
    private static final String REGISTRATION_FORM_VALIDATOR = "registrationFormValidator";
    private static final String LOGIN_FORM_VALIDATOR = "loginFormValidator";
    private static final String AVATAR_VALIDATOR = "avatarValidator";
    private static final String CAPTCHA_VALIDATOR = "captchaValidator";
    private static final String CAPTCHA_SERVICE = "captchaService";
    private static final String CAPTCHA_HANDLER = "captchaHandler";
    private static final String CAPTCHA_HANDLER_TYPE = "captchaHandler";
    private static final String SESSION_CAPTCHA_HANDLER = "sessionCaptchaHandler";
    private static final String COOKIE_CAPTCHA_HANDLER = "cookieCaptchaHandler";
    private static final String APP_CAPTCHA_HANDLER = "appCaptchaHandler";
    private static final String USER_REQUEST_EXTRACTOR = "userExtractor";
    private static final String PRODUCT_FILTER_BEAN_EXTRACTOR = "filterBeanExtractor";
    private static final String AVATAR_REQUEST_EXTRACTOR = "avatarExtractor";
    private static final String REGISTRATION_FORM_REQUEST_EXTRACTOR = "registrationFormExtractor";
    private static final String LOGIN_FORM_REQUEST_EXTRACTOR = "loginFormExtractor";
    private static final String CAPTCHA_REQUEST_EXTRACTOR = "captchaExtractor";

    private final long ONE_MINUTE = 60 * 1000;

    private File avatarsFolder;

    private Cage cage;
    private CaptchaHandler handler;
    private CaptchaService service;
    private Map<String, CaptchaHandler> handlerMap;
    private ConcurrentMap<UUID, Captcha> captchaMap;

    private Repository<Product> productRepository;
    private Repository<Category> categoryRepository;
    private Repository<Manufacturer> manufacturerRepository;

    private UserDAO userDAO;
    private ItemDAO itemDAO = new ItemDAOImpl();
    private InfoOrderedItemDAO infoOrderedItemDAO = new InfoOrderedItemDAOImpl();
    private OrderDAO orderDAO = new OrderDAOImpl();
    private AvatarService avatarService;
    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;
    private ManufacturerService manufacturerService;
    private OrderService orderService;
    private ItemService itemService;

    private Validator<RegistrationForm> registrationFormValidator;
    private Validator<LoginForm> loginFormValidator;
    private Validator<Avatar> avatarValidator;
    private CaptchaValidator captchaValidator;

    private RequestExtractor<User> userExtractor;
    private RequestExtractor<RegistrationForm> registrationFormExtractor;
    private RequestExtractor<LoginForm> loginFormExtractor;
    private RequestExtractor<Captcha> captchaExtractor;
    private RequestExtractor<Avatar> avatarExtractor;
    private RequestExtractor<ProductFilter> productFilterBeanExtractor;

    private DataSource dataSource;
    private TransactionManager transactionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        cage = new GCage();
        captchaMap = new ConcurrentHashMap<>();
        ServletContext context = sce.getServletContext();

        initLog4j(context);
        initDataSource();

        initRepositories();

        initFormValidators(context);
        initCaptchaValidator(context);
        initAvatarValidator(context);

        initHandler(context);
        initUserService(context);
        initAvatarService(context);
        initCaptchaService(context);
        initServices(context);

        initExtractors(context);
    }

    private void initRepositories() {
        productRepository = new MySqlProductRepository();
        categoryRepository = new MySqlCategoryRepository();
        manufacturerRepository = new MySqlManufacturerRepository();
    }

    private void initServices(ServletContext context) {
        productService = new DBProductService(transactionManager, productRepository);
        categoryService = new DBCategoryService(transactionManager, categoryRepository);
        manufacturerService = new DBManufacturerService(transactionManager, manufacturerRepository);
        orderService = new OrderServiceImpl(orderDAO, infoOrderedItemDAO, transactionManager);
        itemService = new ItemServiceImpl(transactionManager, itemDAO);

        context.setAttribute(PRODUCT_SERVICE, productService);
        context.setAttribute(CATEGORY_SERVICE, categoryService);
        context.setAttribute(MANUFACTURER_SERVICE, manufacturerService);
        context.setAttribute(ITEM_SERVICE, itemService);
        context.setAttribute(ORDER_SERVICE, orderService);
    }

    private void initAvatarValidator(ServletContext context) {
        avatarValidator = new AvatarValidator();
        context.setAttribute(AVATAR_VALIDATOR, avatarValidator);
    }

    private void initDataSource() {
        Context envContext;
        try {
            envContext = (Context) new InitialContext().lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/root");
        } catch (NamingException e) {
            LOG.warn("DataSource was not initialize.", e);
        }
    }

    private void initLog4j(ServletContext context) {
        URL log4jUrl = ContextListener.class.getClassLoader().getResource(LOG_PROPERTIES);
        PropertyConfigurator.configure(log4jUrl);
    }

    private void initExtractors(ServletContext context) {
        userExtractor = new UserExtractor();
        registrationFormExtractor = new RegistrationFormExtractor();
        loginFormExtractor = new LoginFormExtractor();
        captchaExtractor = new CaptchaExtractor();
        avatarExtractor = new AvatarExtractor();
        productFilterBeanExtractor = new ProductFilterBeanExtractor();

        context.setAttribute(PRODUCT_FILTER_BEAN_EXTRACTOR, productFilterBeanExtractor);
        context.setAttribute(USER_REQUEST_EXTRACTOR, userExtractor);
        context.setAttribute(REGISTRATION_FORM_REQUEST_EXTRACTOR, registrationFormExtractor);
        context.setAttribute(LOGIN_FORM_REQUEST_EXTRACTOR, loginFormExtractor);
        context.setAttribute(CAPTCHA_REQUEST_EXTRACTOR, captchaExtractor);
        context.setAttribute(AVATAR_REQUEST_EXTRACTOR, avatarExtractor);
    }

    private void initFormValidators(ServletContext context) {
        registrationFormValidator = new RegistrationFormValidator();
        context.setAttribute(REGISTRATION_FORM_VALIDATOR, registrationFormValidator);
        loginFormValidator = new LoginFormValidator();
        context.setAttribute(LOGIN_FORM_VALIDATOR, loginFormValidator);
    }

    private void initCaptchaValidator(ServletContext context) {
        captchaValidator = new CaptchaValidator();
        context.setAttribute(CAPTCHA_VALIDATOR, captchaValidator);
    }

    private void initHandler(ServletContext context) {
        handlerMap = new HashMap<>();
        handlerMap.put(SESSION_CAPTCHA_HANDLER, new SessionCaptchaHandler());
        handlerMap.put(COOKIE_CAPTCHA_HANDLER, new CookieCaptchaHandler(captchaMap));
        handlerMap.put(APP_CAPTCHA_HANDLER, new ApplicationCaptchaHandler(captchaMap));
        String handlerName = context.getInitParameter(CAPTCHA_HANDLER_TYPE);
        handler = handlerMap.get(handlerName);
        context.setAttribute(CAPTCHA_HANDLER, handler);
        if (handler instanceof MappedCaptchaHandler) {
            new CaptchaMapCleaner(captchaMap, ONE_MINUTE).start();
        }
    }

    private void initCaptchaService(ServletContext context) {
        service = new RandomNumberCaptchaService(cage, ONE_MINUTE);
        context.setAttribute(CAPTCHA_SERVICE, service);
    }

    private void initUserService(ServletContext context) {
        transactionManager = new TransactionManager(dataSource);
        userDAO = new MySQLUserDAO();
        List<User> userList = new ArrayList<>();
        userService = new DBUserService(transactionManager, userDAO);
        context.setAttribute(USER_SERVICE, userService);
    }

    private void initAvatarService(ServletContext context) {
        avatarsFolder = new File(context.getInitParameter("avatar.location"));
        avatarService = new AvatarServiceImpl(avatarsFolder);
        context.setAttribute(AVATAR_SERVICE, avatarService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}