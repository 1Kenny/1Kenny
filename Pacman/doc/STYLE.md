# Coding style guideline

Out of self-respect and of respect to the readers of the code, we'll try to fit as much as
possible to the following guidelines.

Note that it's my first time programming in Java and I haven't read that much Java code so
these rules might be non-standard, but at least **we are consistent**.

### Use of camelCase

I don't like it, but Java coders seems to do. Non-capitalised first letter, please.

### 2 lines space between import and class declaration

```java
import model.Pacman;


public class MyClass {
```

### 1 line space after class declaration

```java
public class myClass {

    private int myVar;
}
```

### Attributes first

```java
public class myClass {

    private int myFirstVar;
    private String myOtherVar;

    private int myFunc() {
        return myFirstVar;
    }
}
```

### Private methods second

*See above*

### Then public methods

```java
public class myClass {

    private int myFirstVar;
    private String myOtherVar;

    private int myFunc() {
        return myFirstVar;
    }

    public String getMyOtherVar() {
        return myOtherVar;
    }
}
```

### C style comments for class description, C++ style otherwise

```java
/**
* This is my class, it's a great class.
*/
public class myClass {

    // This a variable
    private int myFirstVar;
    private String myOtherVar;

    // This is a very useless method !
    private int myFunc() {
        return myFirstVar;
    }

    public String getMyOtherVar() {
        return myOtherVar;
    }
}
```
