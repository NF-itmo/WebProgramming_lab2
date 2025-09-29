package org.validator.validation;

import org.validator.annotations.NotNull;
import org.validator.annotations.Number;
import org.validator.annotations.Pattern;
import org.validator.annotations.Size;
import org.validator.validation.validators.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;

public class ValidationController {

    private static final HashMap<Class<?>, Validator<? extends Annotation>> validatorCache = new HashMap<>();

    static {
        validatorCache.put(NotNull.class, new NotNullValidator());
        validatorCache.put(Size.class, new SizeValidator());
        validatorCache.put(Pattern.class, new PatternValidator());
        validatorCache.put(Number.class, new NumberValidator());
    }

    public static <T> void validateObject(T object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);

                Object value = field.get(object);
                validateField(value, field.getAnnotations());
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to validate field: " + field.getName(), e);
            }
        }
    }

    private static <T extends Annotation> void validateField(Object value, T[] annotations) {
        for (Annotation annotation : annotations) {
            validateAnnotation(annotation, value);
        }
    }

    @SuppressWarnings("unchecked")
    private static void validateAnnotation(Annotation annotation, Object value) {
        Class<? extends Annotation> annotationType = annotation.annotationType();

        Validator<Annotation> validator = (Validator<Annotation>) validatorCache.get(annotationType);
        if (validator == null) return;

        for (Annotation metaAnnotation : annotationType.getAnnotations()) validateAnnotation(metaAnnotation, value);

        validator.validate(annotation, value);
    }
}


