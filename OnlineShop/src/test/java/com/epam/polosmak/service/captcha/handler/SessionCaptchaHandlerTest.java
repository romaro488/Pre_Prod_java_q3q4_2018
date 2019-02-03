package com.epam.polosmak.service.captcha.handler;

import com.epam.polosmak.web.bean.Captcha;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SessionCaptchaHandlerTest {
    private static final String CAPTCHA = "captcha";
    private CaptchaHandler handler;

    private Captcha captcha;
    private HttpServletResponse responseMock;
    private HttpServletRequest requestMock;
    private HttpSession sessionMock;


    @Before
    public void setup() {
        responseMock = mock(HttpServletResponse.class);
        requestMock = mock(HttpServletRequest.class);
        sessionMock = mock(HttpSession.class);
        captcha = mock(Captcha.class);

        when(sessionMock.getAttribute(CAPTCHA)).thenReturn(captcha);
        when(requestMock.getSession()).thenReturn(sessionMock);

        handler = new SessionCaptchaHandler();
    }

    @Test
    public void shouldGetCaptchaFromSessionAttributeAndRemoveCaptchaAttribute() {
        assertEquals(captcha, handler.getCaptcha(requestMock));
        verify(sessionMock, times(1)).getAttribute(CAPTCHA);
        verify(sessionMock, times(1)).removeAttribute(CAPTCHA);
    }

    @Test
    public void shouldPutCaptchaIntoSessionAttribute() {
        handler.saveCaptcha(requestMock, responseMock, captcha);
        verify(sessionMock, times(1)).setAttribute(CAPTCHA, captcha);

    }
}
