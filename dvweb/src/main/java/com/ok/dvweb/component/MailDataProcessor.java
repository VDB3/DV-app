package com.ok.dvweb.component;

import com.ok.dvweb.component.sub.EmailValidationResult;
import com.ok.dvweb.util.ConstantsUtil;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MailDataProcessor {

    private static final String EMAIL_REGEX =
            "^[a-z0-9_+&*-]+(?:\\.[a-z0-9_+&*-]+)*@[a-z0-9-]+(?:\\.[a-z0-9-]+)*\\.[a-z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public EmailValidationResult validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return new EmailValidationResult(false, ConstantsUtil.ValidationProcessor.NULL);
        }

        if (email.contains(ConstantsUtil.ValidationProcessor.SPACE_CHARACTER)) {
            return new EmailValidationResult(false, ConstantsUtil.ValidationProcessor.CONTAIN_SPACE);
        }

        if (!email.equals(email.toLowerCase())) {
            return new EmailValidationResult(false, ConstantsUtil.ValidationProcessor.CONTAIN_UPPER);
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            return new EmailValidationResult(false, ConstantsUtil.ValidationProcessor.INVALID_FORMAT);
        }

        return new EmailValidationResult(true, ConstantsUtil.ValidationProcessor.IS_VALID);

    }

}
