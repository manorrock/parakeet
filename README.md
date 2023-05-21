# Manorrock Parakeet

_Note this project has gone into passive mode. See below for an explanation_

[![build](https://github.com/manorrock/parakeet/actions/workflows/build.yml/badge.svg)](https://github.com/manorrock/parakeet/actions/workflows/build.yml)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=manorrock_parakeet&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=manorrock_parakeet)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=manorrock_parakeet&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=manorrock_parakeet)

Manorrock Parakeet delivers you with a light-weight YAML library that is 100% 
self-contained. It can be used to read and write YAML.

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

## What is passive mode?

A project can go into passive mode for either of two reasons. Either the project
is feature complete and no active development is needed. Or the project is no
longer considered a priority. Whatever the reason the end result is the same.

This means:

1. No more scheduled monthly releases.
2. If a bug is filed it is addressed on a best effort basis.
3. No new features are anticipated.
4. Releases are only cut on a needs basis and not more than once a month.
5. If you want your bug or feature to receive attention sponsoring is your best bet.
