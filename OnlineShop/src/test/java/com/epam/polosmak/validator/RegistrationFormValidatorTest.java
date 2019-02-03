package com.epam.polosmak.validator;

import com.epam.polosmak.web.bean.RegistrationForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationFormValidatorTest {
    private static final String FIRST_NAME_ERROR_MSG_PATH = "fname-error-msg";
    private static final String LAST_NAME_ERROR_MSG_PATH = "lname-error-msg";
    private static final String EMAIL_ERROR_MSG_PATH = "email-error-msg";
    private static final String PASSWORD_ERROR_MSG_PATH = "password-error-msg";
    private static final String CONFIRM_PASSWORD_ERROR_MSG_PATH = "password-confirm-error-msg";

    @Mock
    private RegistrationForm formBeenMock;

    private Validator<RegistrationForm> validator;

    @Before
    public void setUp() {
        validator = new RegistrationFormValidator();

        when(formBeenMock.getFirstName()).thenReturn("Correct");
        when(formBeenMock.getLastName()).thenReturn("Correct");
        when(formBeenMock.getEmail()).thenReturn("correct@mail.ru");
        when(formBeenMock.getCheckbox()).thenReturn("on");
        when(formBeenMock.getPassword()).thenReturn("CorrectPassword123");
        when(formBeenMock.getConfirmPassword()).thenReturn("CorrectPassword123");
    }

    @Test
    public void shouldReturnTrueIfAllVariablesIsValid() {
        assertTrue(validator.validate(formBeenMock).isEmpty());
    }

    @Test
    public void shouldNotBeValidIfNameContainsDigits() {

        doReturn("NotCorrect123").when(formBeenMock).getFirstName();
        assertTrue(validator.validate(formBeenMock).containsKey(FIRST_NAME_ERROR_MSG_PATH));
    }

    @Test
    public void shouldNotBeValidIfNameContainsCharactersOtherThanLetters() {

        doReturn("Name+=").when(formBeenMock).getFirstName();
        assertTrue(validator.validate(formBeenMock).containsKey(FIRST_NAME_ERROR_MSG_PATH));
    }

    @Test
    public void shouldNotBeValidIfLastNameContainsDigits() {

        doReturn("NotCorrect123").when(formBeenMock).getLastName();
        assertTrue(validator.validate(formBeenMock).containsKey(LAST_NAME_ERROR_MSG_PATH));
    }

    @Test
    public void shouldNotBeValidIfLastNameContainsCharactersOtherThanLetters() {

        doReturn("Name+=").when(formBeenMock).getLastName();
        assertTrue(validator.validate(formBeenMock).containsKey(LAST_NAME_ERROR_MSG_PATH));
    }

    @Test
    public void shouldNotBeValidIfEmailNotContainsSymbolAt() {

        doReturn("uncorrect.ru").when(formBeenMock).getEmail();
        assertTrue(validator.validate(formBeenMock).containsKey(EMAIL_ERROR_MSG_PATH));
    }

    @Test
    public void shouldNotBeValidIfEmailNotContainsDomain() {

        doReturn("uncorrect@mail").when(formBeenMock).getEmail();
        assertTrue(validator.validate(formBeenMock).containsKey(EMAIL_ERROR_MSG_PATH));
    }

    @Test
    public void shouldNotBeValidIfPasswordLessThenEightSymbols() {
        doReturn("unc").when(formBeenMock).getPassword();
        assertTrue(validator.validate(formBeenMock).containsKey(PASSWORD_ERROR_MSG_PATH));
    }

    @Test
    public void shouldNotBeValidIfPasswordNotConfirm() {
        doReturn("unc").when(formBeenMock).getConfirmPassword();
        assertTrue(validator.validate(formBeenMock).containsKey(CONFIRM_PASSWORD_ERROR_MSG_PATH));
    }
}