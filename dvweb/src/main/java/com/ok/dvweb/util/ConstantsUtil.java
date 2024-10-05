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
                "/actuator/info",
                "/news",
                "/confirm"
        };
        public static final String[] WHITE_LIST_OMIT = {
                "/subscriber",
                "/contact",
                "/update"
        };
    }

    // Constantes relacionadas con los datos de mensajeria
    public static class Messaging {
        public static final String MAIL_FROM = "bogadovd@hotmail.com";
        public static final String MAIL_TO = "etcdev89@gmail.com";
        public static final String SUBJECT = "DV website Contact";
        public static final String SUBJECT_NOTIFY = "Nueva Noticia";
        public static final String SUBJECT_VERIFY = "Verificacion de correo";
        public static final String CONTENT_TYPE = "text/html";
        public static final String CONTENT_IMG = "<p>Ultima noticia</p><br><img src='cid:image1' alt='Imagen adjunta' />";
        public static final String CONTENT = "MENSAJE DE CONTRATACION, <br>NOMBRE: %s " +
                "MENSAJE: %s. <br>DATOS DE CONTACTO: (%s) (%s)";
        public static final String CONTENT_CONFIRM = "<p>Por favor, confirma tu correo haciendo clic " +
                "en el siguiente enlace: " +
                "<a href='https://046c-2800-810-4ff-11e8-e17e-1512-70f3-33a5.ngrok-free.app/confirm?token=%s'>Confirmar</a></p>";
    }
    //"https://4c3a-2800-810-4ff-11e8-bd1f-a670-8978-98df.ngrok-free.app/confirm?token=%s" -- ngrok
    //https://localhost:8080/confirm?token=%s -- local
}
