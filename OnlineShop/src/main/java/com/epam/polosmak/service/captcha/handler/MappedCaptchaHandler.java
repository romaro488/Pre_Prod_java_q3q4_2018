package com.epam.polosmak.service.captcha.handler;

import com.epam.polosmak.web.bean.Captcha;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public abstract class MappedCaptchaHandler implements CaptchaHandler {
    protected ConcurrentMap<UUID, Captcha> captchaMap;

    protected MappedCaptchaHandler(ConcurrentMap<UUID, Captcha> captchaMap) {
        this.captchaMap = captchaMap;
    }
}
