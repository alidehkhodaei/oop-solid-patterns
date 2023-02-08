package logger

interface ILogger {
    val name: String
    fun logError(message: String)
}