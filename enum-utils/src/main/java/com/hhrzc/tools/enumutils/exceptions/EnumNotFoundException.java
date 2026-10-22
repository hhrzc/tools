package com.hhrzc.tools.enumutils.exceptions;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumNotFoundException extends RuntimeException {
    public <T extends Enum<T>> EnumNotFoundException(Class<T> clazz, T[] enums, String value) {
        super(String.format("The enum %s doesn't contain value %s. Available values: [%s]",
                clazz.getName(),
                value,
                Arrays.stream(enums).map(Enum::name).collect(Collectors.joining(", "))));
    }

    public <T extends Enum<T>> EnumNotFoundException(
            Class<T> clazz,
            String value,
            List<String> valuesForReport,
            String methodName) {
        super(String.format("The enum %s value was not found for method '%s' with value %s. Available values: [%s]",
                clazz.getName(),
                methodName,
                value,
                valuesForReport));
    }
}
