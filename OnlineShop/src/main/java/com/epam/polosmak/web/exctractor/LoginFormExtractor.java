package com.epam.polosmak.web.exctractor;

import com.epam.polosmak.web.bean.LoginForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class LoginFormExtractor implements RequestExtractor<LoginForm> {
    private static final Logger LOG = LoggerFactory.getLogger(LoginFormExtractor.class);

    private static final String USER_EMAIL_PARAMETER = "user-email";
    private static final String USER_PASSWORD_PARAMETER = "user-password";

    @Override
    public LoginForm extractFromRequest(HttpServletRequest request) {
        LOG.debug("LoginFormExtractor starts");

        LoginForm loginForm = new LoginForm();
        loginForm.setEmail(request.getParameter(USER_EMAIL_PARAMETER));
        loginForm.setPassword(request.getParameter(USER_PASSWORD_PARAMETER));

        LOG.info("Extracted Form: --> " + loginForm);
        LOG.debug("LoginFormExtractor finished.");
        return loginForm;
    }
}
