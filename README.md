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
    fun calculatePay(payable: Payable) {
        // Implementation code removed for better clarity
        payable.calculatePay(currency)
    }
}
```

## ✅Liskov’s Substitution Principle (Lsp): <a name="lsp"></a>
Subtypes must be replaceable with their base types without affecting the correctness of the program. Overridng methods does not violate the Liskov Substitution Principle (LSP) because it does not change the behavior or functionality of the parent class. Overriding is simply replacing the implementation of a method from the parent class with a different implementation in the child class.

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