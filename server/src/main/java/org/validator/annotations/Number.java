package org.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@NotNull(errorMsg = "Number field is required")
@Size(min = 1, errorMsg = "Number must not be empty")
@Pattern(pattern = "^-?\\d+(\\.\\d+)?$", errorMsg = "Must be a valid number")
public @interface Number {
    String rangeErrorMsg() default "Number must be between %s and %s";
    String stepErrorMsg() default "Number distance from minimum must be divisible by %s";
    double min() default Integer.MIN_VALUE;
    double max() default Integer.MAX_VALUE;
    double step() default 0;
}
