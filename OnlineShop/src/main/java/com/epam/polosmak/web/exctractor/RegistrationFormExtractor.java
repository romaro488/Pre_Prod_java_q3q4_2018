package com.epam.polosmak.web.exctractor;

import com.epam.polosmak.web.bean.RegistrationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class RegistrationFormExtractor implements RequestExtractor<RegistrationForm> {
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationFormExtractor.class);

    private static final String USER_FIRST_NAME_PARAMETER = "user-fname";
    private static final String USER_LAST_NAME_PARAMETER = "user-lname";
    private static final String USER_EMAIL_PARAMETER = "user-email";
    private static final String USER_PASSWORD_PARAMETER = "user-password";
    private static final String CONFIRM_PASSWORD_PARAMETER = "confirm-password";
    private static final String CHECKBOX_PARAMETER = "checkbox";

    @Override
    public RegistrationForm extractFromRequest(HttpServletRequest request) {
        LOG.debug("RegistrationFormExtractor starts");

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setFirstName(request.getParameter(USER_FIRST_NAME_PARAMETER).trim());
        registrationForm.setLastName(request.getParameter(USER_LAST_NAME_PARAMETER).trim());
        registrationForm.setEmail(request.getParameter(USER_EMAIL_PARAMETER).trim());
        registrationForm.setCheckbox(request.getParameter(CHECKBOX_PARAMETER));
        registrationForm.setPassword(request.getParameter(USER_PASSWORD_PARAMETER));
        registrationForm.setConfirmPassword(request.getParameter(CONFIRM_PASSWORD_PARAMETER));
        LOG.info("Extracted Form: --> {}", registrationForm);

        LOG.debug("RegistrationFormExtractor finished.");

        return registrationForm;
    }
}
