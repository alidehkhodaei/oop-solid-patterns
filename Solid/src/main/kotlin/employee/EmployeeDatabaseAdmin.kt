package employee

import logger.ILogger

class EmployeeDatabaseAdmin(
    override val name: String,
    override val totalHoursWorked: Double,
    private val logger: ILogger
) : BaseEmployee {

    fun save() {
        try {
            // Implementation code removed for better clarity
            // Save to database.
            // Use try-catch because it may throw exception.
        } catch (e: Exception) {
            logger.logError(e.message!!)
        }
    }

    override fun work() {
        // Implementation code removed for better clarity
    }
}

