# Out Tool

[![Maven Central](https://img.shields.io/maven-central/v/io.github.hhrzc/out-object)](https://central.sonatype.com/artifact/io.github.hhrzc/out-object)

A tiny utility for emulating C#-style `out` parameters in Java.

Java has no native way to hand a value back out of a method through a parameter — this library papers over that with small mutable holder objects: a single value (`Out`), a pair (`BiOut`), a same-typed pair (`EqualsOut`), and a growable list (`CollectionOut`) — each with sensible `equals()`/`hashCode()` and a shared notion of "has this actually been set yet?"

**Java** · **Zero dependencies** · **Out parameters** · **Functional interfaces**

## Why

Java has no built-in way to hand a value back out of a method through a parameter. Here's the shape that gap usually takes:

```java
// The method's real return type stays clean — the domain object your
// test actually cares about. The raw response rides along as an
// optional out parameter, for the cases where you also need it.
public User createUser(CreateUserRequest request, Out<HttpResponse> rawResponse) {
    HttpResponse response = apiClient.post("/users", request);
    rawResponse.set(response);
    return responseMapper.toUser(response);
}
```

```java
Out<HttpResponse> rawResponse = new OutObject<>();
User createdUser = userService.createUser(request, rawResponse);

// the parsed domain object drives the rest of the test as usual...
assertThat(createdUser.getEmail()).isEqualTo(request.getEmail());

// ...while the out parameter gives you the transport-level details
// too, without changing what createUser() returns for every other caller
assertThat(rawResponse.get().statusCode()).isEqualTo(201);
```

This is the pattern the whole library is named after: an out parameter, C#-style. It's most useful when you don't want (or can't change) the method's primary return type — e.g. it already implements an interface, or the clean domain object is what every other caller expects — but one particular caller also needs something extra that doesn't belong on that return type.

The four types below cover different shapes of "something extra":

- 📦 **`Out<T>`** — a single mutable slot: `get()` / `set(T)`. "Applied" once it holds a non-null value.
- 🔗 **`BiOut<T, K>`** — two independently-typed slots. Only counts as "applied" once both are set.
- ⚖️ **`EqualsOut<T>`** — a `BiOut<T, T>` specialization for holding two values of the same type side by side — e.g. expected vs. actual.
- 📋 **`CollectionOut<T>`** — a growable list slot. `add(...)` requires `set(List)` first — calling it before that throws a clear exception instead of a silent NPE.

## Installation

Gradle:

```groovy
implementation("io.github.hhrzc:out-object:0.1.1")
```

Maven:

```xml
<dependency>
    <groupId>io.github.hhrzc</groupId>
    <artifactId>out-object</artifactId>
    <version>0.1.1</version>
</dependency>
```

## API

Every type shares `CommonInterface`: `reset()` and `isApplied()`.

| Type | Implementation | Notes |
|---|---|---|
| `Out<T>` | `OutObject<T>` | `get()` / `set(T)`. Applied when non-null. |
| `BiOut<T,K>` | `BiOutObject<T,K>` | `setFirst`/`setSecond`, `getFirst`/`getSecond`. Applied when both are non-null. |
| `EqualsOut<T>` | `EqualsOutObjects<T>` | Same as `BiOut`, both slots share one type `T`. |
| `CollectionOut<T>` | `ArrayListOut<T>` | `set(List)` then `add(T...)`. `add()` before `set()` throws `ObjectIsNotInstantiatedException`. |

All implementations override `equals()`/`hashCode()` (and most, `toString()`) based on their held value(s), so two holders with the same contents compare equal.

## Usage

### `Out<T>` — a single value

```java
Out<String> name = new OutObject<>();
name.isApplied();   // -> false
name.set("Alice");
name.get();         // -> "Alice"
name.isApplied();   // -> true
name.reset();       // back to null / not applied
```

### `BiOut<T, K>` — two independently-typed values

```java
BiOut<String, Integer> person = new BiOutObject<>();
person.setFirst("Alice");
person.isApplied();   // -> false, second isn't set yet
person.setSecond(25);
person.isApplied();   // -> true, both are set now
```

### `EqualsOut<T>` — two values of the same type

```java
EqualsOut<String> comparison = new EqualsOutObjects<>();
comparison.setFirst("expected");
comparison.setSecond("actual");
comparison.getFirst().equals(comparison.getSecond()); // -> false
```

### `CollectionOut<T>` — a growable list

```java
CollectionOut<Integer> numbers = new ArrayListOut<>();
numbers.set(new ArrayList<>());
numbers.add(1, 2, 3);
numbers.get(); // -> [1, 2, 3]

CollectionOut<String> unset = new ArrayListOut<>();
unset.add("A"); // throws ObjectIsNotInstantiatedException — call set() first
```