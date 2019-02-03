package com.epam.polosmak.service;

import com.epam.polosmak.web.bean.Captcha;

import java.io.IOException;

public interface CaptchaService {
    Captcha generateCaptcha();

    String drawCaptchaInBase64(Captcha captcha) throws IOException;
}
