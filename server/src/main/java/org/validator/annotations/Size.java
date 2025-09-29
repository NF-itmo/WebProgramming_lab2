package org.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.RECORD_COMPONENT, ElementType.ANNOTATION_TYPE})
@NotNull
public @interface Size {
    String errorMsg() default "String size must be between %d and %d";
    int min() default 0;
    long max() default Integer.MAX_VALUE;
}
