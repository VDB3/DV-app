package com.ok.dvweb.component;

import com.ok.dvweb.component.sub.EmailValidationResult;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RequestDataProcessor {

    public static final String IS_VALID = "El correo electrónico es válido.";
    // Patrón regex para correos electrónicos permitidos, sin mayúsculas ni espacios, con cualquier dominio
    private static final String EMAIL_REGEX = "^[a-z0-9_+&*-]+(?:\\.[a-z0-9_+&*-]+)*@[a-z0-9-]+(?:\\.[a-z0-9-]+)*\\.[a-z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static final String NULO = "El correo electrónico no puede ser nulo o vacío.";
    public static final String CONTAIN_SPACE = "El correo electrónico no puede contener espacios.";
    public static final String CONTAIN_UPPER = "El correo electrónico no puede contener mayúsculas.";
    public static final String INVALID_FORMAT = "El correo electrónico tiene un formato inválido. Asegúrate de que siga el formato general: usuario@dominio.tld.";

    public EmailValidationResult validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return new EmailValidationResult(false, NULO);
        }

        // Verificar espacios en el correo electrónico
        if (email.contains(" ")) {
            return new EmailValidationResult(false, CONTAIN_SPACE);
        }

        // Verificar si hay mayúsculas
        if (!email.equals(email.toLowerCase())) {
            return new EmailValidationResult(false, CONTAIN_UPPER);
        }

        // Verificar el formato general
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            return new EmailValidationResult(false, INVALID_FORMAT);
        }

        return new EmailValidationResult(true, IS_VALID);

    }

}
