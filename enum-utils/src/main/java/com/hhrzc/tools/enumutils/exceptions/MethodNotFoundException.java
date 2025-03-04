package com.hhrzc.tools.enumutils.exceptions;

public class MethodNotFoundException extends RuntimeException {
    public MethodNotFoundException(Class<?> clazz, String methodName, String message) {
        super(
            """
            Error during method invoking occurs.
            Possibly method '%s' do not declared in the enum %s.
            Full error: %s
            """
            .formatted(methodName, clazz.getName(), message)
        );
    }
}
