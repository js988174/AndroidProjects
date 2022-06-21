fun main(args : Array<String>) {
    var a1 = 10
    var a2 = -a1
    println("a2 : ${a2}")

    a2 = a1.unaryMinus()
    println("a2 : ${a2}")

    a2 = +a1
    println("a2 : ${a2}")

    a2 = a1.unaryPlus()

    var a3 = true
    var a4 = !a3
    println("a4 : ${a4}")

    a4 = a3.not()
    println("a4 : ${a4}")

    var a5 = 10
    var a6 = 10
    var a7 = a5++        // a5 = a5 + 1
    var a8 = a6--        // a6 = a6 - 1
    println("a5, a7 : ${a5}, ${a7}")  // 11, 10
    println("a6, a8 : ${a6}, ${a8}")  // 9, 10

    a5 = 10
    a6 = 10
    a7 = ++a5
    a8 = --a6
    println("a5, a7 : ${a5}, ${a7}")  // 11, 11
    println("a6, a8 : ${a6}, ${a8}")  // 9, 9

    a5 = 10
    a6 = 10
    a7 = a5.inc()
    a8 = a6.inc()
    println("a5, a7 : ${a5}, ${a7}") // 10, 11
    println("a6, a8 : ${a6}, ${a8}") // 10, 11

    a5 = 10
    a6 = 3
    a7 = a5 + a6
    a8 = a5.plus(a6)
    println("a7 : ${a7}") // 13
    println("a8 : ${a8}") // 13

    a7 = a5 - a6
    a8 = a5.minus(a6)
    println("a7 : ${a7}") // 7
    println("a8 : ${a8}") // 7

    a7 = a5 * a6
    a8 = a5.times(a6)
    println("a7 : ${a7}") // 30
    println("a8 : ${a8}") // 30

    a7 = a5 / a6
    a8 = a5.div(a6)
    println("a7 : ${a7}") // 3
    println("a8 : ${a8}") // 3

    var a10 = a6..a5
    var a11 = a6.rangeTo(a5)
    println("a10 : ${a10}") // 3..10
    println("a11 : ${a11}") // 3..10

    // 코틀린은 대입 연산자를 사용하지 않는다.

    var a13 = 10
    var a14 = a13 == 10
    println("a14 : ${a14}") // true

    a14 = a13.equals(10)
    println("a14 : ${a14}") // true

    a14 = a13 != 10
    println("a14 : ${a14}") // false

    a14 = !(a13.equals(10))
    println("a14 : ${a14}") // false

    a14 = a13 > 20
    println("a14 : ${a14}") // false

    a14 = a13.compareTo(20) > 0
    println("a14 : ${a14}") // false

    a14 = a13 < 20
    println("a14 : ${a14}") // true

    a14 = a13.compareTo(20) < 0
    println("a14 : ${a14}") // true

    a14 = a13 >= 10
    println("a14 : ${a14}") // true

    a14 = a13.compareTo(10) >= 0
    println("a14 : ${a14}") // true

    a14 = a13 <= 10
    println("a14 : ${a14}") // true

    a14 = a13.compareTo(10) <= 10
    println("a14 : ${a14}") // true
}