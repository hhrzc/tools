# Changelog — enum-utils

All notable changes to `io.github.hhrzc:enum-utils` are documented here.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [0.1.02] - 2026-10-22

### Changed
- **Minimum Java version is now 11** (was 21). The library is compiled with
  `--release 11`, so it can be used in projects on Java 11, 17, 21 and newer.
  Gradle metadata now declares `org.gradle.jvm.version = 11`.
- `MethodNotFoundException` and `EnumNotFoundException` build their messages with
  `String.format(...)` instead of text blocks and `String.formatted(...)` (Java 15+).
- Exception messages have corrected wording:
    - `doesn't contains value` → `doesn't contain value`
    - `The enum X doesn't found for method ...` → `The enum X value was not found for method ...`
    - `Possibly method '...' do not declared` → `Possibly method '...' is not declared`

### Fixed
- Projects on Java versions lower than 21 could not resolve 0.1.01: Gradle rejected
  the dependency because the published metadata required Java 21.

## [0.1.0] - 2026-09-06
- Initial release on Maven Central.