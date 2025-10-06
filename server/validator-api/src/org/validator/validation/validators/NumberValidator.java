package org.validator.validation.validators;

import org.validator.annotations.Number;
import org.validator.validation.exceptions.ValidationException;

public class NumberValidator implements Validator<Number> {
    @Override
    public void validate(Number annotation, Object value) {
        if (value instanceof String str) {
            double num = Double.parseDouble(str);
            if (num < annotation.min() || num > annotation.max()) {
                throw new ValidationException(
                        String.format(annotation.rangeErrorMsg(), annotation.min(), annotation.max())
                );
            }
            if (annotation.step() != 0 && (num-annotation.min())%annotation.step() != 0) {
                throw new ValidationException(
                        String.format(annotation.stepErrorMsg(), annotation.step())
                );
            }
        }
    }
}