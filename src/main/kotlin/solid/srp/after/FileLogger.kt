package srp.after

import java.io.File

class FileLogger(
    var fileName: String
) {
    fun log(message: String) {
        File(fileName).writeText(message)
    }
}