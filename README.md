# Solid Principles

🔴The Solid Principles are a set of coding principles that provide a guide to writing maintainable, scalable, and robust software.
These principles are often considered as best practices in object-oriented programming and software design, and are used to develop maintainable, scalable, and reusable software. 

🔴The Solid principles are not limited to classes, it can be applied to various software entities such as modules, functions, and components.

🔴Solid is primarily designed for object-oriented programming, but its principles can be applied to other programming paradigms as well.
The Solid principles provide general guidelines for writing maintainable and scalable code, and they are not limited to a particular programming language or paradigm.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/solid.png)


## ✅Single Responsibility Principle (Srp):
A class should have only one reason to change, meaning it should have only one responsibility.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/srp.png)

Before:
```kotlin
class EmployeeDatabaseAdmin(
    override val name: String,
    override val totalHoursWorked: Double
) : BaseEmployee {

    fun save() {
        try {
            // Implementation code removed for better clarity
            // Save to database.
            // Use try-catch because it may throw exception.
        } catch (e: Exception) {
            // ❌ This class violates the Single Responsibility Principle because it has two tasks.
            File("logFile.txt").writeText(e.message!!)
        }
    }
    
    override fun work() {
        // Implementation code removed for better clarity
    }

}

```
After:
```kotlin
class EmployeeDatabaseAdmin(
    override val name: String,
    override val totalHoursWorked: Double
) : BaseEmployee {

    fun save() {
        try {
            // Implementation code removed for better clarity
            // Save to database.
            // Use try-catch because it may throw exception.
        } catch (e: Exception) {
            ✅ Ok
            val logger=FileLogger("logFile")
            logger.logError(e.message!!)
        }
    }
    override fun work() {
        // Implementation code removed for better clarity
    }
}
```


## ✅Open/Closed Principle (Ocp):
Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/ocp.png)

Before:
```kotlin
class EmployeeFinances(override val name: String, override val totalHoursWorked: Double) : BaseEmployee {

     fun calculatePay(typeEmployee: TypeEmployee, id :Int) {
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
    
  enum class TypeEmployee{FULL_TIME,PART_TIME,CONTRACTOR}
}

```
After:

We don't need enum after refactoring, so delete it.
```kotlin
interface EmployeeFinances : BaseEmployee {
     fun calculatePay(id: Int)
}

class EmployeeFinancesForContractor(override val name: String, override val totalHoursWorked: Double) : EmployeeFinances {
    override fun calculatePay(id: Int) {
        // Implementation code removed for better clarity
    }
    override fun work() {
        // Implementation code removed for better clarity
    }
}

class EmployeeFinancesForFTE(override val name: String, override val totalHoursWorked: Double) : EmployeeFinances {
    override fun calculatePay(id: Int) {
        // Implementation code removed for better clarity
    }
    override fun work() {
        // Implementation code removed for better clarity
    }
}

class EmployeeFinancesForPTE(override val name: String, override val totalHoursWorked: Double) : EmployeeFinances {
   override fun calculatePay(id: Int) {
        // Implementation code removed for better clarity
    }
    override fun work() {
        // Implementation code removed for better clarity
    }
 }

```

## ✅Liskov’s Substitution Principle (Lsp):
Subtypes must be replaceable with their base types without affecting the correctness of the program. Overridng methods does not violate the Liskov Substitution Principle (LSP) because it does not change the behavior or functionality of the parent class. Overriding is simply replacing the implementation of a method from the parent class with a different implementation in the child class.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/lsp.png)

Before:
```kotlin
class EmployeeFinancesForFTE(override val name: String, override val totalHoursWorked: Double) : EmployeeFinances {
    // ❌ A violation of the LSP (Liskov Substitution Principle) occurs when I use an instance of 'EmployeeFinancesForFTE'
    instead of 'EmployeeFinances', causing the program to behave incorrectly when the ID is negative.
    override fun calculatePay(id: Int) {
        if (id < 0) {
            throw IllegalArgumentException()
        }
        // Other implementation
    }

    override fun calculateRewards(id: Int) {
        // Implementation code removed for better clarity
    }

    override fun work() {
        // Implementation code removed for better clarity
    }
}
```

After:
```kotlin
class EmployeeFinancesForFTE(override val name: String, override val totalHoursWorked: Double) : EmployeeFinances {
    // ✅ We can substitute 'EmployeeFinancesForFTE' for 'EmployeeFinances' without affecting the program's correctness.
    override fun calculatePay(id: Int) {
        // Implementation code removed for better clarity
    }

    override fun calculateRewards(id: Int) {
        // Implementation code removed for better clarity
    }

    override fun work() {
        // Implementation code removed for better clarity
    }
}
```

## ✅Interface Segregation Principle (Isp):
Clients should not be forced to depend on interfaces they do not use.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/isp.png)

## ✅Dependency Inversion Principle (Dip):
High-level modules should not depend on low-level modules, both should depend on abstractions.

![alt text](https://github.com/alidehkhodaei/solid-principles/raw/main/photos/dip.png)
 
