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
