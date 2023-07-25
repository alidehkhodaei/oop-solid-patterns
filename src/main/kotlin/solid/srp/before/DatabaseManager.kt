package srp.before

import java.io.File

class DatabaseManager(private val databaseName: String) {

    fun connectToDatabase() {
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

