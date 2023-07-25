package dip.before

import dip.after.FileLogger

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

