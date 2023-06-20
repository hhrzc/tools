package com.hrzc.lib.tools.utils;

import com.hrzc.lib.tools.exceptions.EnumNotFoundException;
import com.hrzc.lib.tools.exceptions.MethodNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class EnumUtils {

    /**
     * This method retrieves a case-insensitive enum object.
     * The name of the enum instance must be equal to the provided {@param value}
     *
     * @param clazz - Class of the enum instance
     * @param value - the name of expected enum
     * @return Instance of the enum
     */
    public static <T extends Enum<T>> T getEnumByValue(Class<T> clazz, String value) {
        T[] enums = clazz.getEnumConstants();
        Optional<T> result = Arrays.stream(enums)
                .filter(p -> p.name().toLowerCase().trim().equals(value.toLowerCase().trim()))
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EnumNotFoundException(clazz, enums, value);
        }
    }

    /**
     * This method retrieves a case-insensitive enum object.
     * Difference between {@link #getEnumByValue(Class, String)} is that this
     * method search first enum that contains {@param value} in his own name.
     * Note: if enum has 2 and more constraints the method returns first founded.
     *
     * @param clazz - Class of the enum instance
     * @param value - the name of expected enum
     * @return Instance of the enum
     */
    public static <T extends Enum> T getEnumByValueContainsEnumName(Class<T> clazz, String value) {
        T[] enums = clazz.getEnumConstants();
        Optional<T> result = Arrays.stream(enums)
                .filter(p -> value.toLowerCase().trim().contains(p.name().toLowerCase().trim()))
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EnumNotFoundException(clazz, enums, value);
        }
    }

    /**
     * This method retrieves enum by the value that returned by certain method.
     * To define a method, the method name is used as a string value.
     *
     * For the instance we have some enum:
     * class enum Example{
     * FOO("foo value"), BAR("bar value");
     * private String value;
     * Example(String value){this.value = value;}
     * public getValue(){return value;}}
     *
     * For this enum if we need to receive BAR instance by "bar value" string,
     * we have to use this method in following way:
     * {Example example = EnumUtils.getEnumByMethodName(
     * Exapmle.class,"getValue","bar value");}
     *
     * @param clazz      - Class of the enum instance
     * @param methodName - the string value of the method. This method will be used
     *                   with reflection to invoke this method and receiving of the
     *                   value to comparing with {@param value}
     * @param value      - expected value that have to be returned by {@param methodName}
     * @return Instance of the enum
     */
    public static <T extends Enum> T getEnumByMethodName(Class<T> clazz, String methodName, String value) {
        value = value.trim().toLowerCase();
        value = value.replace(" ", "");
        try {
            Method getEnumValue = clazz.getMethod(methodName);
            T[] enums = clazz.getEnumConstants();
            List<String> valuesForReport = new ArrayList<>();
            for (T t :
                    enums) {
                String actualValue = getEnumValue.invoke(t).toString().trim().toLowerCase();
                actualValue = actualValue.replace(" ", "");
                valuesForReport.add(actualValue);
                if (value.equals(actualValue)) {
                    return t;
                }
            }
            throw new EnumNotFoundException(clazz, value, valuesForReport, methodName);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new MethodNotFoundException(clazz, methodName, e.getMessage());
        }
    }

    /**
     * This method retrieves enum by the function that returns searching value.
     *
     * @param clazz      - Class of the enum instance
     * @param func       - function that returns value for the searching
     * @param value      - expected value that have to be returned by {@param func}
     * @return Instance of the enum
     */
    public static <T extends Enum> T getEnumByFunction(Class<T> clazz,
                                                       Function<T, String> func,
                                                       String value) {
        value = value.trim().toLowerCase();
        value = value.replace(" ", "");
        try {
            T[] enums = clazz.getEnumConstants();
            List<String> valuesForReport = new ArrayList<>();
            for (T t :
                    enums) {
                String actualValue = func.apply(t);
                actualValue = actualValue
                        .replace(" ", "")
                        .toLowerCase();
                valuesForReport.add(actualValue);
                if(value.equals(actualValue)){
                    return t;
                }
            }
            throw new EnumNotFoundException(clazz, value, valuesForReport, func.toString());
        } catch (RuntimeException e) {
            throw new MethodNotFoundException(
                    clazz,
                    func.toString(),
                    e.getMessage()
            );
        }
    }

}
