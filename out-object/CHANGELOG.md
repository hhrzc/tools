# Changelog — out-object

All notable changes to `io.github.hhrzc:out-object` are documented here.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [0.1.12] - 2026-10-22

### Changed
- **Minimum Java version is now 11** (was 21). The library is compiled with
  `--release 11`, so it can be used in projects on Java 11, 17, 21 and newer.
  Gradle metadata now declares `org.gradle.jvm.version = 11`.
- `OutObject.equals()` no longer uses pattern matching for `instanceof` (Java 16+);
  it now uses a classic type check and cast. Behavior is unchanged.

### Fixed
- Projects on Java versions lower than 21 could not resolve 0.1.11: Gradle rejected
  the dependency because the published metadata required Java 21.

## [0.1.1] - 2026-09-06
- Initial release on Maven Central.