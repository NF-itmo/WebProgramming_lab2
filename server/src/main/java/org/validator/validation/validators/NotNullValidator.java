package org.validator.validation.validators;

import org.validator.annotations.NotNull;
import org.validator.validation.exceptions.ValidationException;

public class NotNullValidator implements Validator<NotNull> {
    @Override
    public void validate(NotNull annotation, Object value) {
        if (value == null) {
            throw new ValidationException(annotation.errorMsg());
        }
    }
}