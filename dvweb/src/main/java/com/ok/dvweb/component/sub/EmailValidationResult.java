package com.ok.dvweb.component.sub;

import lombok.Data;

@Data
public class EmailValidationResult {

    private boolean isValid;
    private String message;

    public EmailValidationResult(boolean isValid, String message) {
        this.isValid = isValid;
        this.message = message;
    }
}
