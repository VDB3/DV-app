package com.ok.dvweb.component;

import com.ok.dvweb.component.sub.EmailValidationResult;
import com.ok.dvweb.util.ConstantsUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MailDataProcessorTest {

    private MailDataProcessor dataProcessor;

    @BeforeEach
    public void setup() {
        this.dataProcessor = new MailDataProcessor();
    }

    @Test
    public void whenCalledValidationWithCorrectlyFormat_shouldBeReturnIsValid() {
        String mail = "usuario@dominio.tld";
        EmailValidationResult result = dataProcessor.validateEmail(mail);
        assertTrue(result.isValid());
        assertEquals(ConstantsUtil.ValidationProcessor.IS_VALID, result.getMessage());
    }

    @Test
    public void whenCalledValidationWhitNullOrEmpty_shouldBeInvalid() {
        EmailValidationResult result = dataProcessor.validateEmail("");
        assertFalse(result.isValid());
        assertEquals(ConstantsUtil.ValidationProcessor.NULL, result.getMessage());

        EmailValidationResult result2 = dataProcessor.validateEmail(null);
        assertFalse(result2.isValid());
        assertEquals(ConstantsUtil.ValidationProcessor.NULL, result2.getMessage());
    }

    @Test
    public void whenCalledValidationWhitSpace_shouldBeInvalid(){
        String mail = "usuario @dominio.tld";
        EmailValidationResult result = dataProcessor.validateEmail(mail);
        assertFalse(result.isValid());
        assertEquals(ConstantsUtil.ValidationProcessor.CONTAIN_SPACE, result.getMessage());
    }

    @Test
    public void whenCalledValidationWhitUpperCase_shouldBeInvalid(){
        String mail = "Usuario@dominio.tld";
        EmailValidationResult result = dataProcessor.validateEmail(mail);
        assertFalse(result.isValid());
        assertEquals(ConstantsUtil.ValidationProcessor.CONTAIN_UPPER, result.getMessage());
    }

    @Test
    public void whenCalledValidationWhitInvalidFormat_shouldBeInvalid(){
        String mail = "usuario@dominio.tld.";
        EmailValidationResult result = dataProcessor.validateEmail(mail);
        assertFalse(result.isValid());
        assertEquals(ConstantsUtil.ValidationProcessor.INVALID_FORMAT, result.getMessage());
    }
}
