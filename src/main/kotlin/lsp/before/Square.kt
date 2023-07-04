package lsp.before

class Square(side: Int) : Rectangle(side, side) {

    override fun calculateArea(): Int {
        if (height != width)
            throw IllegalArgumentException("The width and height of a square should be equal!")
        return width * width
    }
}
