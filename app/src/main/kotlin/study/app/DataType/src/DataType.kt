import java.time.LocalDateTime

fun main(args : Array<String>) {
    println("정수 : ${100}")
    println("정수 (Long) : ${100L} ")
    println("실수 : ${55.55}")
    println("실수 (Float): ${55.55f}")
    println(1000000000)
    println(1_000_000_000)
    println("문자열입니다")
    println('a')

    var a1 = 100;
    println("a1 : ${a1}")
    // a1 = 55.55
    var a2 = "안녕하세요"
    println("a2 : ${a2}")

    var a3 : Int = 100
    println("a3 : ${a3}")

    // var a4 : String = 100

    var a4 = 100
    println("a4 : ${a4}")

    a4 = 200
    println("a4 : ${a4}")

    val a5 = 100
    println("a5 : ${a5}")

    // a5 = 200
}