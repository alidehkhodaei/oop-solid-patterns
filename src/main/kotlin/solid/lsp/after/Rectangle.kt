package lsp.after

class Rectangle(var width: Int, var height: Int) : Shape {
    override fun calculateArea(): Int {
        return width * height
    }
}