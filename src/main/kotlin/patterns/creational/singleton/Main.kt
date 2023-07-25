package creational.singleton

fun main() {
    val instance1 = Singleton
    val instance2 = Singleton

    // Both instance1 and instance2 refer to the same singleton object
    println(instance1 == instance2) // true
}
