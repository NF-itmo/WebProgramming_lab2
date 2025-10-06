package org.validator.validation.validators;
import java.lang.annotation.Annotation;

@FunctionalInterface
public interface Validator<A extends Annotation> {
    void validate(A annotation, Object value);
}