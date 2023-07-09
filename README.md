<h1 id="my-title"> Solid Principles </h1>

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/solid.png)

The Solid Principles are a set of coding principles that provide a guide to writing maintainable, scalable, and robust software.
The Solid principles are not limited to classes, it can be applied to various software entities such as modules, functions, and components.

- `S` [Single Responsibility Principle (SRP)](#srp)
- `O` [Open-Closed Principle (OCP)](#ocp)
- `L` [Liskov Substitution Principle (LSP)](#lsp)
- `I` [Interface Segregation Principle (ISP)](#isp)
- `D` [Dependency Inversion Principle (DIP)](#dip)

## ✅Single Responsibility Principle (Srp): <a name="srp"></a>
A class should have only one reason to change, meaning it should have only one responsibility.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/srp.png)

Before:

```kotlin
class DatabaseManager(private val databaseName: String) {
    
    fun connectToDatabase(){
        // Implementation code removed for better clarity.
    }

    fun saveDataToDatabase() {
        try {
            // Implementation code removed for better clarity.
            // Perform some operation that may throw an exception.
        } catch (e: Exception) {
            /* 
            ❌ This code violates the Single Responsibility Principle (SRP)
             because the `DatabaseManager` class has two responsibilities:
            1. Saving data to the database.
            2. Writing an error message to a log file.
            */
            File("logFile.txt").writeText(e.message!!)
        }
    }
}
```
After:

```kotlin
class DatabaseManager(private val databaseName: String) {

    fun connectToDatabase(){
        // Implementation code removed for better clarity.
    }

    fun saveDataToDatabase() {
        try {
            // Implementation code removed for better clarity.
            // Perform some operation that may throw an exception.
        } catch (e: Exception) {
            // ✅ Ok
            val logger = FileLogger("logFile.txt")
            logger.log(e.message!!)
        }
    }

}
``` 

In this refactored code, the `DatabaseManager` class only focuses on saving data to the database, while the `FileLogger` class is responsible
for logging errors. Each class now has a single responsibility, and any changes related to error logging won't affect the `DatabaseManager` class.

## ✅Open/Closed Principle (Ocp): <a name="ocp"></a>
Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/ocp.png)

Before:

```kotlin
class PayCalculator(var currency: String) {

    // We use currency in implementation.

    fun calculatePay(typeEmployee: TypeEmployee) {
        if (typeEmployee == TypeEmployee.FULL_TIME) {
            // Implementation code removed for better clarity
        } else if (typeEmployee == TypeEmployee.PART_TIME) {
            // Implementation code removed for better clarity
        } else if (typeEmployee == TypeEmployee.CONTRACTOR) {
            // Implementation code removed for better clarity
        } else {
            // Implementation code removed for better clarity
        }
    }

    enum class TypeEmployee { FULL_TIME, PART_TIME, CONTRACTOR }

   // Other methods
}
```
- The class isn't closed for modification because modifications are needed whenever a new employee type is added.
- The class isn't open for extension because you would need to modify the existing class and add new conditions to handle new employee types.

After:

We don't need enum after refactoring, so delete it.
```kotlin
interface Payable{
    fun calculatePay()
}
```

```kotlin
class FullTimeEmployeePayable(var hoursWorked:Double) : Payable {
    override fun calculatePay() {
        // Implementation code removed for better clarity
    }
}
class PartTimeEmployeePayable(var hourlyRate:Double) : Payable {
    override fun calculatePay() {
        // Implementation code removed for better clarity
    }
}
class ContractorPayable(var projectDuration:Double) : Payable {
    override fun calculatePay() {
        // Implementation code removed for better clarity
    }
}
```

```kotlin
class PayCalculator(var currency: String) {

    // We use currency in implementation.

    fun calculatePay(payable: Payable) {
        // Implementation code removed for better clarity
        payable.calculatePay()
    }
   // Other methods
}
```

## ✅Liskov’s Substitution Principle (Lsp): <a name="lsp"></a>
Subtypes must be replaceable with their base types without affecting the correctness of the program.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/lsp.png)

Before:

```kotlin
open class Rectangle(var width: Int, var height: Int) {
    open fun calculateArea(): Int {
        return width * height
    }
}
```
```kotlin
class Square(side: Int) : Rectangle(side, side) {

    override fun calculateArea(): Int {
        if (height != width)
            throw IllegalArgumentException("The width and height of a square should be equal!")
        return width * width
    }
}
```

```kotlin
fun main() {
    val rectangle: Rectangle = getDefaultRectangle()
    rectangle.width = 7
    rectangle.height = 8
    println(rectangle.calculateArea())
}

private fun getDefaultRectangle(): Rectangle {
    return Rectangle(3, 6)
}

private fun getDefaultSquare(): Rectangle {
    return Square(3)
}
```
The program encounters a problem when we replace the rectangle (`getDefaultRectangle`)
with a square (`getDefaultSquare`).

After:

```kotlin
interface Shape {
    fun calculateArea(): Int
}
```
```kotlin
class Rectangle(var width: Int, var height: Int) : Shape {
    override fun calculateArea(): Int {
        return width * height
    }
}
```
```kotlin
class Square(var side: Int) : Shape {
    override fun calculateArea(): Int {
        return side * side
    }
}
```
## ✅Interface Segregation Principle (Isp): <a name="isp"></a>
Clients should not be forced to depend on interfaces they do not use.

Bad example
```kotlin
interface Animal {
    fun fly()
    fun swim()
}
```
Good example
```kotlin
interface Flyable {
    fun fly()
}
interface Swimmable  {
    fun swim()
}
```

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/isp.png)

Before:

```kotlin
interface Worker {
    fun work()
    fun eat()
}
```
```kotlin
class Robot(private val numberRobot:Int) : Worker {
    override fun work() {
        // Implementation code removed for better clarity.
    }

    override fun eat() {
        // ❌ ISP (Interface Segregation Principle) violation occurs when a class does not need a method.
        // This method is not applicable to a robot.
        throw UnsupportedOperationException("Robots don't eat!")
    }

}


class Human(private val name:String) : Worker {
    override fun work() {
        // Implementation code removed for better clarity.
    }

    override fun eat() {
        // Implementation code removed for better clarity.
    }
}

```

After:

```kotlin
interface Workable {
    fun work()
}

interface Eatable {
    fun eat()
}
```
```kotlin
class Human(private val name:String) : Workable, Eatable {
    override fun work() {
        // Implementation code removed for better clarity.
    }

    override fun eat() {
        // Implementation code removed for better clarity.
    }
}

class Robot(private val numberRobot:Int) : Workable {
    override fun work() {
        // Implementation code removed for better clarity.
    }
}

```






## ✅Dependency Inversion Principle (Dip): <a name="dip"></a>
High-level modules should not depend on low-level modules, both should depend on abstractions.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/dip.png)

❌ Problem: Suppose we have another logger class, then should we create another class like `DatabaseManager` again?

This class basically only depends on FileLogger, but what if we need DatabaseLogger?

Before:

```kotlin
class DatabaseManager(private val databaseName: String) {

    fun connectToDatabase(){
        // Implementation code removed for better clarity.
    }

    fun saveDataToDatabase() {
        try {
            // Implementation code removed for better clarity.
            // Perform some operation that may throw an exception.
        } catch (e: Exception) {
            val logger = FileLogger("logFile.txt")
            logger.log(e.message!!)
        }
    }

}
```

After:

We create a `Logger` interface and two classes implement it. This `DatabaseManager` class works with any subclass of Logger and depend on abstractions.

 ```kotlin
interface Logger {
    fun log(message: String)
}

class FileLogger(var fileName: String) : Logger {
    override fun log(message: String) {
        File(fileName).writeText(message)
    }
}

class DatabaseLogger(var tableName: String) : Logger {
    override fun log(message: String) {
        // Implementation code removed for better clarity
    }
}
```

```kotlin
class DatabaseManager(
    private val databaseName: String,
    private val logger: Logger
) {

    fun connectToDatabase(){
        // Implementation code removed for better clarity.
        /* In this method, the `logger` is also used because there might
         be an exception occurring during the database connection process.*/
    }

    fun saveDataToDatabase() {
        try {
            // Implementation code removed for better clarity.
            // Perform some operation that may throw an exception.
        } catch (e: Exception) {
            logger.log(e.message!!)
        }
    }

}
```
