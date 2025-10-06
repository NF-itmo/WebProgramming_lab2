package org.validator;

import org.validator.validation.ValidationController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ValidatedRecordFactory {
    @SuppressWarnings("unchecked")
    public static <T extends Record> T create(Class<T> recordClass, Object... args) {
        try {
            Constructor<T> constructor = (Constructor<T>) recordClass.getDeclaredConstructors()[0];
            T record = constructor.newInstance(args);

            ValidationController.validateObject(record);

            return record;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Record can't be created", e);
        }
    }
}