[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless/factor-extractor/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.wnameless/factor-extractor)


factor-extractor
=============
Extract the return values from any specified annotated methods of Java beans into a Java Map.

Installation with Maven:
``` xml
<dependency>
  <groupId>com.github.wnameless</groupId>
  <artifactId>factor-extractor</artifactId>
  <version>${latest.version}</version>
</dependency>
```

An annotated class
```java
public class FactorBean {
  @Factor("id")
  public String id() {
    return "id";
  }

  @Factor("num")
  public int num() {
    return 123;
  }
}
```

FactorExtrator example:
```java
Map<String, Object> factors = FactorExtractor.extract(new FactorBean());
System.out.println(factors.get("id"));
// Output: id
System.out.println(factors.get("num"));
// Output: 123
```
