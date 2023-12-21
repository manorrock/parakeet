# Manorrock Parakeet

[![build](https://github.com/manorrock/parakeet/actions/workflows/build.yml/badge.svg)](https://github.com/manorrock/parakeet/actions/workflows/build.yml)

Manorrock Parakeet delivers you with a light-weight YAML library that is 100% 
self-contained. It can be used to read and write YAML.

## Prerequisites

1. Java 21

## Writing YAML

The example below writes out the Java String as YAML.

```java
  StringWriter stringWriter = new StringWriter();
  YAMLWriter writer = new YAMLWriter(stringWriter);
  writer.writeObject("Any object");
```

## Reading YAML

The example below reads in the YAML as a Java String.

```java
  StringReader stringReader = new StringReader("'this is a YAML string'");
  YAMLReader reader = new YAMLReader(stringReader);
  String string = reader.readObject(String.class.getName());
```

## Maven coordinates

The Maven coordinates are as follows.

```xml
  <dependency>
    <groupId>com.manorrock.parakeet</groupId>
    <artifactId>parakeet</artifactId>
    <version>y.m.p</version>
  </dependency>
```

<!--
Please see [Maven central](https://repo1.maven.org/maven2/com/manorrock/parakeet)
for the latest version information
  -->

## How do I contribute?

See [Contributing](CONTRIBUTING.md)

## Our code of Conduct

See [Code of Conduct](CODE_OF_CONDUCT.md)

## Important notice

Note if you file issues, answer questions and/or issue pull requests you agree
that those contributions will be owned by Manorrock.com and that Manorrock.com
can use those contributions in any manner Manorrock.com so desires.
