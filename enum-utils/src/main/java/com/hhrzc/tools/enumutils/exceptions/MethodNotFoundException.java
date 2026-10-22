package com.hhrzc.tools.enumutils.exceptions;

public class MethodNotFoundException extends RuntimeException {
    public MethodNotFoundException(Class<?> clazz, String methodName, String message) {
        super(
                String.format("Error during method invoking occurs.%n"
                        + "Possibly method '%s' is not declared in the enum %s.%n"
                        + "Full error: %s%n", methodName, clazz.getName(), message)
        );
    }
}
