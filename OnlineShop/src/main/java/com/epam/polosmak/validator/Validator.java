package com.epam.polosmak.validator;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T request);
}
