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
