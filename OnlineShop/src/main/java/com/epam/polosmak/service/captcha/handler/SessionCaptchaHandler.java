package com.epam.polosmak.service.captcha.handler;

import com.epam.polosmak.web.bean.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionCaptchaHandler implements CaptchaHandler {
    private static final Logger LOG = LoggerFactory.getLogger(SessionCaptchaHandler.class);
    private static final String CAPTCHA = "captcha";

    @Override
    public void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha) {
        request.getSession().setAttribute(CAPTCHA, captcha);
        LOG.info("Set the session attribute: captcha --> {}", captcha);
    }

    @Override
    public Captcha getCaptcha(HttpServletRequest request) {
        Captcha captcha = (Captcha) request.getSession().getAttribute(CAPTCHA);
        LOG.info("Session attribute: captcha --> {}", captcha);
        request.getSession().removeAttribute(CAPTCHA);
        LOG.info("Removed session attribute: captcha.");
        return captcha;
    }
}
