fun main(args : Array<String>) {
    f1()
    f2(100, 55.55)
    f3(100, 55.55)
    f3(100)
    f3(a1 = 200, a2 = 66.66)
    f3(a2 = 77.77, a1= 300)
    f3(a2 = 88.88)
    f4()

    var a1 = f5()
    println("a5 : ${a1}")

    f6()
    f6(100)
    f6(55.55)
    f6(100, 200)

    f7()
}

fun f1() {
    println("f1함수가 호출되었습니다")
}

fun f2(a1 : Int, a2 : Double) {
    println("f2가 호출되었습니다.")
    println("a1 : ${a1}")
    println("a2 : ${a2}")
}

fun f3(a1 : Int = 0, a2 : Double = 0.0) {
    println("f3이 호출되었습니다")
    println("a1 : ${a1}")
    println("a2 : ${a2}")
}

fun f4() : Unit {
    println("f4가 호출되었습니다.")
}

fun f5() : Int {
    return 100
}

fun f6() {
   println("매개 변수가 없는 f6")
}

fun f6(a1 : Int) {
    println("정수 값 한개를 받는 f6 : ${a1}")
}

fun f6(a1 : Double) {
    println("실수 값 한개를 받는 f6 : ${a1}")
}

fun f6(a1 : Int, a2 : Int) {
    println("정수 값 두개를 받는 f6 : ${a1}, ${a2}")
}

fun f7() {
    fun f8() {
        println("f8 함수가 호출되었습니다")
    }

    println("f7 함수가 호출되었습니다")
    f8()
}