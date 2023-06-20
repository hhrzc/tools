package com.hrzc.lib.tools.exceptions;

import java.util.List;

public class EnumNotFoundException extends RuntimeException {
    public <T extends Enum> EnumNotFoundException(Class<T> clazz, T[] enums, String value) {
        super("The enum %s doesn't contains value %s. Available values: [%s]"
                .formatted(clazz.getName(),
                        value,
                        enums));
    }

    public <T extends Enum> EnumNotFoundException(
            Class<T> clazz,
            String value,
            List<String> valuesForReport,
            String methodName) {
        super("The enum %s doesn't found for method '%s' with value %s. Available values: [%s]"
                .formatted(clazz.getName(),
                        methodName,
                        value,
                        valuesForReport));
    }
}
