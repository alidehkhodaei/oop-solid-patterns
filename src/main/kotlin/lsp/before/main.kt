package lsp.before

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