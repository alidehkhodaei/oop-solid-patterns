package dip.after

import java.io.File

class FileLogger(var fileName: String) : Logger {
    override fun log(message: String) {
        File(fileName).writeText(message)
    }
}