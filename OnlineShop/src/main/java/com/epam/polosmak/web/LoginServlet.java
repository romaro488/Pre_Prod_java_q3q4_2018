package com.epam.polosmak.web;

import com.epam.polosmak.entity.User;
import com.epam.polosmak.service.UserService;
import com.epam.polosmak.validator.LoginFormValidator;
import com.epam.polosmak.validator.Validator;
import com.epam.polosmak.web.bean.LoginForm;
import com.epam.polosmak.web.exctractor.LoginFormExtractor;
import com.epam.polosmak.web.exctractor.RequestExtractor;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    private static final String LOGIN_PAGE = "/loginPage";
    private static final String LOGIN_SERVLET = "/login";

    private static final String USER_BEAN = "userBean";

    private static final String USER_SERVICE = "userService";
    private static final String LOGIN_FORM_REQUEST_EXTRACTOR = "loginFormExtractor";
    private static final String LOGIN_FORM_VALIDATOR = "loginFormValidator";

    private static final String ERRORS = "errors";
    private final static String ACCESS_ERROR_PATH = "access-error-msg";
    private final static String ACCESS_ERROR_MSG = "Wrong email or password!";
    private final static String USER_DOES_NOT_EXIST_MSG = "User with this email does not exist!";

    private UserService userService;
    private RequestExtractor<LoginForm> loginFormRequestExtractor;

    private Validator<LoginForm> formValidator;


    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);

        loginFormRequestExtractor = (LoginFormExtractor) getServletContext().getAttribute(LOGIN_FORM_REQUEST_EXTRACTOR);
        formValidator = (LoginFormValidator) getServletContext().getAttribute(LOGIN_FORM_VALIDATOR);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doGet() called");
        LOG.debug("Go to forward address --> " + LOGIN_PAGE);
        getServletContext().getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("doPost() called");
        LoginForm loginForm = loginFormRequestExtractor.extractFromRequest(request);
        Map<String, String> errors = formValidator.validate(loginForm);
        if (errors.isEmpty()) {
            User user = userService.getUser(loginForm.getEmail());
            LOG.info("Get user by email:" + loginForm.getEmail() + " -->  {}", user);
            checkUserAccess(request, response, loginForm.getPassword(), errors, user);
        } else {
            backToLogin(request, response, errors);
        }
    }

    private void checkUserAccess(HttpServletRequest request, HttpServletResponse response, String password, Map<String, String> errors, User user) throws IOException {
        if (Objects.nonNull(user)) {

            if (BCrypt.checkpw(password, user.getPassword())) {
                LOG.info("User sign up: --> {}", user);
                request.getSession().setAttribute(USER_BEAN, user);
                response.sendRedirect(request.getContextPath());
            } else {
                LOG.info("The entered password is not identical to the stored password. ");
                errors.put(ACCESS_ERROR_PATH, ACCESS_ERROR_MSG);
                backToLogin(request, response, errors);
            }
        } else {
            LOG.info("User not exist.");
            errors.put(ACCESS_ERROR_PATH, USER_DOES_NOT_EXIST_MSG);
            backToLogin(request, response, errors);
        }
    }

    private void backToLogin(HttpServletRequest request, HttpServletResponse response, Map<String, String> errors) throws IOException {
        request.getSession().setAttribute(ERRORS, errors);
        LOG.info("Set the session attribute:" + ERRORS + " -->  {}", errors);
        String forward = request.getContextPath() + LOGIN_SERVLET;
        LOG.debug("Go to redirect address -->  {}", forward);
        response.sendRedirect(forward);
    }
}
