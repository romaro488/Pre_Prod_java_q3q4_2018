package com.epam.polosmak.service.captcha.handler;

import com.epam.polosmak.web.bean.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CaptchaHandler {

    void saveCaptcha(HttpServletRequest request, HttpServletResponse response, Captcha captcha);

    Captcha getCaptcha(HttpServletRequest request);
}
