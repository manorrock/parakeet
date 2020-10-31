# Manorrock Parakeet

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

The example below reads in the YAML as a Java String 

```java
  StringReader stringReader = new StringReader("'this is a YAML string'");
  YAMLReader reader = new YAMLReader(stringReader);
  String reader.readObject(String.class.getName());
```

## Maven dependency

```xml
  <dependency>
    <groupId>com.manorrock.parakeet</groupId>
    <artifactId>parakeet</artifactId>
    <version>y.m.p</version>
  </dependency>
```

## How do I contribute?

See [Contributing](CONTRIBUTING.md)

## Our code of Conduct

See [Code of Conduct](CODE_OF_CONDUCT.md)

## Important notice

Note if you file issues, answer questions and/or issue pull requests you agree
that those contributions will be owned by Manorrock.com and that Manorrock.com
can use those contributions in any manner Manorrock.com so desires.
