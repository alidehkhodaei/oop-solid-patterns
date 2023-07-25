package lsp.after

class Square(var side: Int) : Shape {
    override fun calculateArea(): Int {
        return side * side
    }
}