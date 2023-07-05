package dip.after

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

