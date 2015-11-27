# belteshazzar GeoJSON Java Beans

This is a Java bean implementation of GeoJSON using Jackson serialization annotations. It can be used when implementing JAX-RS RESTful services that can directly read and write GeoJson.

See https://github.com/FasterXML/jackson for Jackson docs.

See http://www.geojson.org for GeoJSON spec.

# Use with Maven

Functionality of this package is contained in 
Java package `com.belteshazzar.geojson`.

To use the package, you need to use following Maven dependency:

```xml
<dependency>
  <groupId>com.belteshazzar</groupId>
  <artifactId>geojson</artifactId>
  <version>0.0.4</version>
</dependency>
```

## Non-Maven Download

For non-Maven use cases, you download jars from [Central Maven repository](http://repo1.maven.org/maven2/com/belteshazzar/geojson/0.0.4/geojson-0.0.4.jar).
