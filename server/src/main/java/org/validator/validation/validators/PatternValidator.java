package org.validator.validation.validators;

import org.validator.annotations.Pattern;
import org.validator.validation.exceptions.ValidationException;

public class PatternValidator implements Validator<Pattern> {
    @Override
    public void validate(Pattern annotation, Object value) {
        if (value instanceof String str) {
            if (!str.matches(annotation.pattern())) {
                throw new ValidationException(annotation.errorMsg());
            }
        }
    }
}