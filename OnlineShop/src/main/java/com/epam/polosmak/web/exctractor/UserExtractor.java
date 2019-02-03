package com.epam.polosmak.web.exctractor;

import com.epam.polosmak.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class UserExtractor implements RequestExtractor<User> {
    private static final Logger LOG = LoggerFactory.getLogger(UserExtractor.class);

    private static final String USER_FIRST_NAME_PARAMETER = "user-fname";
    private static final String USER_LAST_NAME_PARAMETER = "user-lname";
    private static final String USER_EMAIL_PARAMETER = "user-email";
    private static final String USER_PASSWORD_PARAMETER = "user-password";
    private static final String CHECKBOX_PARAMETER = "checkbox";

    @Override
    public User extractFromRequest(HttpServletRequest request) {
        LOG.debug("UserExtractor starts.");

        User user = new User();
        user.setFirstName(request.getParameter(USER_FIRST_NAME_PARAMETER).trim());
        user.setLastName(request.getParameter(USER_LAST_NAME_PARAMETER).trim());
        user.setEmail(request.getParameter(USER_EMAIL_PARAMETER).trim());
        user.setPassword(request.getParameter(USER_PASSWORD_PARAMETER));
        user.setSignUp(Objects.nonNull(request.getParameter(CHECKBOX_PARAMETER)));
        LOG.info("Extracted user:  --> {}", user);

        LOG.debug("CaptchaExtractor finished.");
        return user;
    }
}
