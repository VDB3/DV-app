package com.ok.dvweb.util;

public class ConstantsUtil {

    // Constantes relacionadas con la validacion del correo electronico
    public static class ValidationProcessor {
        public static final String IS_VALID = "El correo electrónico es válido.";
        public static final String NULL = "El correo electrónico no puede ser nulo o vacío.";
        public static final String CONTAIN_SPACE = "El correo electrónico no puede contener espacios.";
        public static final String CONTAIN_UPPER = "El correo electrónico no puede contener mayúsculas.";
        public static final String INVALID_FORMAT = "El correo electrónico tiene un formato inválido. " +
                "Asegúrate de que siga el formato general: usuario@dominio.tld.";
        public static final String SPACE_CHARACTER = " ";
    }

    // Constantes relacionadas con el acceso de los endpoints
    public static class Security {
        public static final String[] WHITE_LIST = {
                "/actuator/health",
                "/actuator/info"
        };
        public static final String[] WHITE_LIST_OMIT = {
                "/subscriber",
                "/contact"
        };
    }

    // Constantes relacionadas con los datos de mensajeria
    public static class Messaging {
        public static final String MAIL_FROM = "bogadovd@hotmail.com";
        public static final String MAIL_TO = "etcdev89@gmail.com";
        public static final String SUBJECT = "NUEVO PROSPECTO";
        public static final String CONTENT = "Hola amador!, tenes un mensaje de: %s, dice: %s. " +
                "te dejo los datos de contacto: (%s) (%s)";
    }

}
