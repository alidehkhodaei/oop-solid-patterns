package lsp.before

open class Rectangle(var width: Int, var height: Int) {
    open fun calculateArea(): Int {
        return width * height
    }
}
