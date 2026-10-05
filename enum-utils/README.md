# EnumUtils

[![Maven Central](https://img.shields.io/maven-central/v/io.github.hhrzc/enum-utils)](https://central.sonatype.com/artifact/io.github.hhrzc/enum-utils)

A small reflection-based utility library for resolving Java enum constants — by exact name (case-insensitive), by a value contained in the name, by the result of a named method, by a custom lookup function, or just at random.

Every lookup miss throws a clear, dedicated exception listing what values were actually available.

**Java** · **Reflection** · **Zero dependencies** · **Enum lookup**

## Why

Enum constants often need to be looked up by something other than their exact Java identifier.

- 🔤 **Case-insensitive by name** — `getEnumByValue` matches an enum constant's name regardless of case or surrounding whitespace.
- 🧩 **Partial name match** — `getEnumByValueContainsEnumName` finds the first constant whose name appears inside a longer string.
- 🪞 **Lookup by method result** — `getEnumByMethodName` invokes a named method on each constant via reflection and matches on its return value — handy for enums that carry a display value.
- 🧠 **Lookup by function** — `getEnumByFunction` does the same thing without reflection — pass any `Function<T, String>` instead of a method name.

## Installation

Gradle:

```groovy
implementation("io.github.hhrzc:enum-utils:0.1.0")
```

Maven:

```xml
<dependency>
    <groupId>io.github.hhrzc</groupId>
    <artifactId>enum-utils</artifactId>
    <version>0.1.0</version>
</dependency>
```

## API

All methods are static, on `EnumUtils`.

| Method | What it does |
|---|---|
| `getEnumByValue(Class, String)` | Case-insensitive, trimmed exact match against each constant's name. |
| `getEnumByValueContainsEnumName(Class, String)` | Returns the first constant whose name is contained within the given value. Returns the first match if more than one qualifies. |
| `getEnumByMethodName(Class, String, String)` | Invokes the named method (via reflection) on each constant and matches its `toString()` result against the given value. |
| `getEnumByFunction(Class, Function<T,String>, String)` | Same idea as above, but you supply a function instead of a reflected method name — no reflection involved. |
| `getRandomEnum(Class)` | Returns a random constant from the given enum class. Throws `IllegalArgumentException` if the class isn't an enum. |

### Errors

- Every lookup miss throws `EnumNotFoundException` with the value you searched for and the full list of values that were actually available.
- A bad method name (via `getEnumByMethodName`) throws `MethodNotFoundException` instead.

## Usage

Given an enum that carries its own display value:

```java
enum Example {
    FOO("foo value"), BAR("bar value");

    private final String value;
    Example(String value) { this.value = value; }
    public String getValue() { return value; }
}
```

Lookup by method name (reflection):

```java
Example example = EnumUtils.getEnumByMethodName(
        Example.class, "getValue", "bar value");
// -> Example.BAR
```

Lookup by function (no reflection):

```java
Example example = EnumUtils.getEnumByFunction(
        Example.class, Example::getValue, "bar value");
// -> Example.BAR
```

Case-insensitive lookup by name:

```java
Example example = EnumUtils.getEnumByValue(Example.class, "  Bar ");
// -> Example.BAR
```

Partial name match:

```java
Example example = EnumUtils.getEnumByValueContainsEnumName(Example.class, "status_BAR_active");
// -> Example.BAR
```

Random constant:

```java
Example example = EnumUtils.getRandomEnum(Example.class);
```