package com.epam.polosmak.validator;

import com.epam.polosmak.web.bean.Captcha;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class CaptchaValidatorTest {
    private static final String RETURNED_VALUE = "value";
    private static final String CAPTCHA_ERROR_MSG_PATH = "confirm-captcha-error-msg";
    private final UUID captchaId = UUID.randomUUID();
    @Mock
    private Captcha captcha;
    @Mock
    private Captcha storedCaptcha;

    private CaptchaValidator validator;

    @Before
    public void setUp() {
        validator = new CaptchaValidator();

        doReturn(RETURNED_VALUE).when(captcha).getValue();
        doReturn(1L).when(captcha).getLifetime();
        doReturn(captchaId).when(captcha).getId();

        doReturn(RETURNED_VALUE).when(storedCaptcha).getValue();
        doReturn(1L).when(storedCaptcha).getLifetime();
        doReturn(captchaId).when(storedCaptcha).getId();
    }

    @Test
    public void shouldReturnEmptyMapIfCaptchaIsValid() {

        assertTrue(validator.validate(captcha, storedCaptcha).isEmpty());
    }

    @Test
    public void shouldReturnMapWithErrorWhenCaptchaIsNull() {
        captcha = null;
        assertTrue(validator.validate(captcha, storedCaptcha).containsKey(CAPTCHA_ERROR_MSG_PATH));
    }

    @Test
    public void shouldReturnMapWithErrorWhenStoredCaptchaIsNull() {
        storedCaptcha = null;
        assertTrue(validator.validate(captcha, storedCaptcha).containsKey(CAPTCHA_ERROR_MSG_PATH));
    }

    @Test
    public void shouldReturnMapWithErrorWhenCaptchaLifetimeBiggerThenStoredCaptchaLifetime() {
        doReturn(storedCaptcha.getLifetime() + 1L).when(captcha).getLifetime();
        Assert.assertTrue(validator.validate(captcha, storedCaptcha).containsKey(CAPTCHA_ERROR_MSG_PATH));
    }

    @Test
    public void shouldReturnMapWithErrorWhenCaptchaValuesIsDifferent() {
        doReturn("differentValue").when(storedCaptcha).getValue();
        Assert.assertTrue(validator.validate(captcha, storedCaptcha).containsKey(CAPTCHA_ERROR_MSG_PATH));
    }

}


