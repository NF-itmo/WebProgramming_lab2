package org.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.RECORD_COMPONENT, ElementType.ANNOTATION_TYPE})
@NotNull
public @interface Pattern {
    String errorMsg() default "Pattern mismatch";
    String pattern();
}
